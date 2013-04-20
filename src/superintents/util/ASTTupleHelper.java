package superintents.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.ui.texteditor.ITextEditor;

public class ASTTupleHelper {
	public ASTRewrite rewriter;
	public ITextEditor editor;
	public ICompilationUnit unit;
	public CompilationUnit compilationUnit;
	public MethodDeclaration currentMethod;

	public ASTTupleHelper(ASTRewrite rewriter, ITextEditor editor, ICompilationUnit unit, MethodDeclaration currentMethod, CompilationUnit compilationUnit) {
		super();
		this.rewriter = rewriter;
		this.editor = editor;
		this.unit = unit;
		this.currentMethod = currentMethod;
		this.compilationUnit = compilationUnit;
	}
}
