package superintents.util;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.ui.texteditor.ITextEditor;

public class ASTTupleHelper {
	public AST ast;
	public ASTRewrite rewriter;
	public ITextEditor editor;
	public ICompilationUnit unit;
	public CompilationUnit compilationUnit;
	public MethodDeclaration currentMethod;

	public ASTTupleHelper(AST ast, ASTRewrite rewriter, ITextEditor editor, ICompilationUnit unit, MethodDeclaration currentMethod, CompilationUnit compilationUnit) {
		super();
		this.ast = ast;
		this.rewriter = rewriter;
		this.editor = editor;
		this.unit = unit;
		this.currentMethod = currentMethod;
		this.compilationUnit = compilationUnit;
	}
}
