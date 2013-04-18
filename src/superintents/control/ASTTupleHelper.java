package superintents.control;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.ui.texteditor.ITextEditor;

public class ASTTupleHelper {
	public ASTRewrite rewriter;
	public ITextEditor editor;
	public ICompilationUnit unit;
	public MethodDeclaration currentMethod;
	public ASTNode ast;
	
	public ASTTupleHelper(ASTRewrite rewriter, ITextEditor editor, ICompilationUnit unit, MethodDeclaration currentMethod, ASTNode ast) {
		super();
		this.rewriter = rewriter;
		this.editor = editor;
		this.unit = unit;
		this.currentMethod = currentMethod;
		this.ast = ast;
	}
}
