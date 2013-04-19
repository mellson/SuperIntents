package superintents.control;

import java.util.ArrayList;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.*;
import org.eclipse.ui.texteditor.*;
import org.eclipse.jface.text.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.ui.JavaUI;
import intentmodel.impl.*;
import transformers.Java2AST;

public class SIHelper {
	public static void insertIntent(SuperIntentImpl intentImplementaion) {
		ArrayList<ASTNodeWrapper> nodeWrappers = Java2AST.transformSuperIntent(intentImplementaion);
		try {
			insertNodes(nodeWrappers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static ASTTupleHelper getASTTupleHelper() {
		ASTRewrite rewriter = null;
		ICompilationUnit unit = null;
		ITextEditor editor = getEditor();
		MethodDeclaration currentMethod = null;
		int currentMethodOffset = 0;
		IEditorInput editorInput = editor.getEditorInput();
		IJavaElement elem = JavaUI.getEditorInputJavaElement(editorInput);
		if (elem instanceof ICompilationUnit) {
			unit = (ICompilationUnit) elem;
			CompilationUnit astRoot = parse(unit);
			AST ast = astRoot.getAST();
			rewriter = ASTRewrite.create(ast);
			TypeDeclaration typeDecl = (TypeDeclaration) astRoot.types().get(0);
			currentMethodOffset = getCurrentMethodOffset(typeDecl.getMethods());
			currentMethod = typeDecl.getMethods()[currentMethodOffset];
		}
		return new ASTTupleHelper(rewriter, editor, unit, currentMethod, currentMethodOffset);
	}

	// This method will check whether the caret is placed inside the given statement and return the innermost ASTNode to insert new nodes in.
	protected static ASTNode getDeepestNode(ASTNode astNode, int caretOffset) {
		ASTNode resultNode = null;
		if (astNode != null) {
			// find where the statements starts and stops
			int statementStartPos = ((ASTNode) astNode).getStartPosition();
			int statementEndPos = ((ASTNode) astNode).getStartPosition() + astNode.getLength();
			// if the caret is placed in a statement...
			if (caretOffset > statementStartPos && caretOffset < statementEndPos) {
				// ..We check all of the properties
				for (Object structuralPropertyDescriptor : astNode.structuralPropertiesForType()) {
					Object currentNode = astNode.getStructuralProperty((StructuralPropertyDescriptor) structuralPropertyDescriptor);
					if (currentNode != null) {
						// If it is a block, we look further into the node
						if (currentNode.getClass() == Block.class) {
							int nodeStart = ((ASTNode) currentNode).getStartPosition();
							int nodeEnd = ((ASTNode) currentNode).getStartPosition() + ((ASTNode) currentNode).getLength();
							// Is the caret inside this node, we recursively apply this method
							if (caretOffset > nodeStart && caretOffset < nodeEnd) {
								ASTNode node = getDeepestNode((ASTNode) currentNode, caretOffset);
								// If there was no block nested inside the current node, set the result to the current one
								if (node == null)
									resultNode = (ASTNode) currentNode;
								else
									resultNode = node;
							}
						} else
							// If it is not a block return null
							resultNode = null;
					}
				}
			}
		}
		return resultNode;
	}

	private static void insertNodes(ArrayList<ASTNodeWrapper> nodeWrappers) throws MalformedTreeException, BadLocationException, JavaModelException {
		// Initialize values
		ASTTupleHelper helper = getASTTupleHelper();
		Block block = helper.currentMethod.getBody();
		int blockOffset = block.getStartPosition();
		int caretOffset = getCaretOffset(helper.editor);
		int nodeStatementOffset = 0;
		int statementOffset = blockOffset;
		ASTNode nestedNode = null;
		ListRewrite listRewrite = null;

		// Check if the caret is placed in a nested block inside the current method
		for (Object o : block.statements())
			nestedNode = getDeepestNode((ASTNode) o, caretOffset);

		// If we are inside a nested block set that as the main block to insert into
		if (nestedNode != null)
			block = (Block) nestedNode;

		for (ASTNodeWrapper nodeWrapper : nodeWrappers) {
			// Get the offset where to insert the current node
			for (Object o : block.statements()) {
				statementOffset += o.toString().length();
				nodeStatementOffset += (statementOffset < caretOffset) ? 1 : 0;
			}
			CompilationUnit compilationUnit = (CompilationUnit) block.getRoot();
			switch (nodeWrapper.type) {
			case NORMAL_CODE:
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(nodeWrapper.astNode, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				break;
			case COMMENT:
				ASTNode commentNode = helper.rewriter.createStringPlaceholder(nodeWrapper.comment, ASTNode.EMPTY_STATEMENT);
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(commentNode, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				break;
			case CALLBACK_METHOD:
				TypeDeclaration typeDeclaration = (TypeDeclaration) compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(typeDeclaration, typeDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertAfter(nodeWrapper.astNode, helper.currentMethod, null);
				break;
			case FIELD:
				TypeDeclaration fieldDeclaration = (TypeDeclaration) compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(fieldDeclaration, fieldDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertFirst(nodeWrapper.astNode, null);
				break;
			case IMPORT:
				listRewrite = helper.rewriter.getListRewrite(compilationUnit, CompilationUnit.IMPORTS_PROPERTY);
				listRewrite.insertFirst(nodeWrapper.astNode, null);
				break;
			default:
				break;
			}
		}

		TextEdit edits = helper.rewriter.rewriteAST();
		Document document = new Document(helper.unit.getSource());
		edits.apply(document);
		helper.unit.getBuffer().setContents(document.get());

		// Jump to the position right after the inserted node and focus the editor
		helper.editor.selectAndReveal(edits.getExclusiveEnd(), 0);
		helper.editor.setFocus();
	}

	protected static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null);
	}

	private static ITextEditor getEditor() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		IWorkbenchPage editor = window.getActivePage();
		IEditorPart part = editor.getActiveEditor();

		if (!(part instanceof AbstractTextEditor))
			return null;
		return (ITextEditor) part;
	}

	private static int getCaretOffset(ITextEditor editor) {
		int offset = ((ITextSelection) editor.getSelectionProvider().getSelection()).getOffset();
		return offset;
	}

	private static int getCurrentMethodOffset(MethodDeclaration[] methods) {
		int result = 0;
		int caretOffset = getCaretOffset(getEditor());
		for (int i = 0; i < methods.length; i++) {
			if (caretOffset >= methods[i].getStartPosition() && caretOffset <= methods[i].getLength() + methods[i].getStartPosition())
				result = i;
		}
		return result;
	}
}