package superintents.util;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class VariableNameASTVisitor extends ASTVisitor {

	boolean method = false;
	String name;

	public VariableNameASTVisitor(String name) {
		this.name = name;
	}

	public boolean visit(VariableDeclarationFragment node) {
		if (node.getName().toString().equals(name))
			method = true;
		return false;
	}
	
	public boolean visit(SingleVariableDeclaration node) {
		if (node.getName().toString().equals(name))
			method = true;
		return false;
	}

	public boolean getExists() {
		return method;
	}
}
