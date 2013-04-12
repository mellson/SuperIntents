package superintents.control;

import org.eclipse.jdt.core.dom.ASTNode;

public class ASTNodeWrapper {

	public ASTNode node;
	public boolean placeInsideMethod;
	public boolean isComment;
	public String comment;
	
	public ASTNodeWrapper(ASTNode node) {
		this.node = node;
		this.placeInsideMethod = true;
		this.isComment = false;
	}
	
	public ASTNodeWrapper(ASTNode node, boolean placeInsideMethod) {
		this.node = node;
		this.placeInsideMethod = placeInsideMethod;
		this.isComment = false;
	}
	
	public ASTNodeWrapper(String comment, boolean placeInsideMethod) {
		this.placeInsideMethod = placeInsideMethod;
		this.isComment = true;
		this.comment = "// " + comment;
	}

}
