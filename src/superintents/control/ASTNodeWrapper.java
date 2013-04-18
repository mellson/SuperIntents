package superintents.control;

import org.eclipse.jdt.core.dom.ASTNode;

public class ASTNodeWrapper {

	public ASTNode node;
	public boolean isCallbackMethod;
	public boolean isComment;
	public String comment;
	
	public ASTNodeWrapper(ASTNode node) {
		this.node = node;
		this.isCallbackMethod = false;
		this.isComment = false;
	}
	
	public ASTNodeWrapper(ASTNode node, boolean isCallbackMethod) {
		this.node = node;
		this.isCallbackMethod = isCallbackMethod;
		this.isComment = false;
	}
	
	public ASTNodeWrapper(String comment) {
		this.isCallbackMethod = false;
		this.isComment = true;
		this.comment = "// " + comment;
	}

}
