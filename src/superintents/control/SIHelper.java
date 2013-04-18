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
		ArrayList<ASTNodeWrapper> nodes = Java2AST.transformSuperIntent(intentImplementaion);
		try {
			insertNodeIntoCurrentMethod(nodes);
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
	
	/**
	 * This method will check whether the caret is placed inside the given statement and return the innermost ASTNode to insert new nodes in.
	 * @param o A statement
	 * @param caretOffset the offset of the caret
	 * @return the ASTnode to insert new nodes in
	 */
	protected static ASTNode isCaretInStatement(Statement o, int caretOffset) {
		ASTNode resultNode = null;

		// find where the statements starts and stops
		int statementStartPos = ((Statement) o).getStartPosition();
		int statementEndPos = ((Statement) o).getStartPosition() + ((Statement) o).getLength();

		// if the caret is placed in a statement...
		if (caretOffset > statementStartPos && caretOffset < statementEndPos) {
			if (o.getClass().equals(Block.class)) {
				for (Object s : ((Block) o).statements()) {
					return isCaretInStatement((Statement) s, caretOffset);
				}
			}

			ChildPropertyDescriptor cpd = null;

			// ...we check that statements structural properties....
			for (Object s : o.structuralPropertiesForType()) {
				if (s.getClass() == ChildPropertyDescriptor.class) {
					// ...to find out if it has a "BODY" property (ex. ForStatements have these)
					if (((ChildPropertyDescriptor) s).getId().equals("body"))
						cpd = (ChildPropertyDescriptor) s;
				}
			}
			
			if (cpd != null)
				resultNode = (ASTNode) isCaretInStatement((Statement) o.getStructuralProperty(cpd), caretOffset);
			else
				resultNode = o;
		}
		return resultNode;
	}

	private static void insertNodeIntoCurrentMethod(ArrayList<ASTNodeWrapper> nodes) throws MalformedTreeException, BadLocationException, JavaModelException {
		ASTTupleHelper helper = getASTTupleHelper();
		Block block = helper.currentMethod.getBody();

		// Where in the statement list of the method should the node be added?
		int blockOffset = block.getStartPosition();
		int caretOffset = getCaretOffset(helper.editor);

		// Running through the nodes in reversed order
		for (int i = nodes.size() - 1; i >= 0; i--) {
			ASTNodeWrapper node = nodes.get(i);
			int nodeStatementOffset = 0;
			// Run through all statements and compare their position to the carets position
			// If the statement is before the caret, increase the nodes insert point by 1 which leads to inserting after that statement
			int statementOffset = blockOffset;
			Block nestedStatement = null;
			
			for (Object o : block.statements()) {
				statementOffset += o.toString().length();
				nodeStatementOffset += (statementOffset < caretOffset) ? 1 : 0;
				nestedStatement = (Block) isCaretInStatement((Statement) o, caretOffset);
			}
			
			ListRewrite listRewrite = null;
			CompilationUnit compilationUnit = (CompilationUnit) block.getRoot();
			switch (node.type) {
			case NORMAL_CODE:
				if (nestedStatement != null) {
					nodeStatementOffset = 0; // FIX THIS!!!
					listRewrite = helper.rewriter.getListRewrite(nestedStatement, Block.STATEMENTS_PROPERTY);
				} else
					listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(node.node, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				break;
			case COMMENT:
				ASTNode commentNode = helper.rewriter.createStringPlaceholder(node.comment, ASTNode.EMPTY_STATEMENT);
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(commentNode, nodeStatementOffset, null);
				break;
			case CALLBACK_METHOD:
				TypeDeclaration typeDeclaration = (TypeDeclaration) compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(typeDeclaration, typeDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertAt(node.node, helper.currentMethodOffset + 1, null);
				break;
			case FIELD:
				break;
			case IMPORT:
				//ImportDeclaration importDeclaration = (ImportDeclaration) compilationUnit.imports().get(0);
				//listRewrite = helper.rewriter.getListRewrite(importDeclaration, importDeclaration.);
				//listRewrite.insertAt(node.node, helper.currentMethodOffset + 1, null);
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