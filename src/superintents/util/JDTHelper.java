package superintents.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.ITextEditor;


public class JDTHelper {
	protected static ASTTupleHelper getASTTupleHelper() {
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
		return new ASTTupleHelper(rewriter, editor, unit, currentMethod);
	}

	protected static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null);
	}

	protected static ITextEditor getEditor() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		IWorkbenchPage editor = window.getActivePage();
		IEditorPart part = editor.getActiveEditor();

		if (!(part instanceof AbstractTextEditor))
			return null;
		return (ITextEditor) part;
	}

	protected static int getCaretOffset(ITextEditor editor) {
		int offset = ((ITextSelection) editor.getSelectionProvider().getSelection()).getOffset();
		return offset;
	}

	protected static int getCurrentMethodOffset(MethodDeclaration[] methods) {
		int result = 0;
		int caretOffset = getCaretOffset(getEditor());
		for (int i = 0; i < methods.length; i++) {
			if (caretOffset >= methods[i].getStartPosition() && caretOffset <= methods[i].getLength() + methods[i].getStartPosition())
				result = i;
		}
		return result;
	}
}
