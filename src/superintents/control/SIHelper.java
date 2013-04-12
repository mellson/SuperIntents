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
		for (ASTNodeWrapper node : nodes) {
			try {
				if (node.placeInsideMethod) {
					insertNodeIntoCurrentMethod(node);
				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void insertNodeIntoCurrentMethod(ASTNodeWrapper node) throws MalformedTreeException, BadLocationException, JavaModelException {
		ITextEditor editor = getEditor();
		IEditorInput editorInput = editor.getEditorInput();
		IJavaElement elem = JavaUI.getEditorInputJavaElement(editorInput);
		if (elem instanceof ICompilationUnit) {
			ICompilationUnit unit = (ICompilationUnit) elem;
			CompilationUnit astRoot = parse(unit);
			AST ast = astRoot.getAST();
			ASTRewrite rewriter = ASTRewrite.create(ast);
			TypeDeclaration typeDecl = (TypeDeclaration) astRoot.types().get(0);
			int offset = getCurrentMethodOffset(typeDecl.getMethods());
			MethodDeclaration methodDecl = typeDecl.getMethods()[offset];
			Block block = methodDecl.getBody();

			// Where in the statement list of the method should the node be added?
			int nodeStatementOffset = 0;
			int nodeLength = 0;
			int blockOffset = block.getStartPosition();
			int caretOffset = getCaretOffset(editor);

			/*
			 * Run through all statements and compare their position to the carets position. If the statement is before the caret, increase the nodes insert point by 1.
			 */
			int statementOffset = blockOffset;
			for (Object o : block.statements()) {
				statementOffset += o.toString().length();
				nodeStatementOffset += (statementOffset < caretOffset) ? 1 : 0;
			}

			ListRewrite listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
			if (node.isComment) {
				ASTNode commentNode = rewriter.createStringPlaceholder(node.comment, ASTNode.EMPTY_STATEMENT);
				listRewrite.insertAt(commentNode, nodeStatementOffset, null);
				nodeLength += node.comment.length() + 3;
			} else {
				listRewrite.insertAt(node.node, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				nodeLength += node.node.toString().length() + 2;
			}

			// Delete the current line when inserting!!!
			TextEdit edits = rewriter.rewriteAST();
			Document document = new Document(unit.getSource());
			edits.apply(document);
			unit.getBuffer().setContents(document.get());

			// Jump to the position right after the inserted node and focus the editor
			caretOffset += nodeLength;
			editor.selectAndReveal(caretOffset, 0);
			editor.setFocus();
		}
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