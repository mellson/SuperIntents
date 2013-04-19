package superintents.util;

import org.eclipse.jdt.core.dom.ASTNode;

public class ASTNodeWrapper {
	public enum NodeType {
		NORMAL_CODE, CALLBACK_METHOD, COMMENT, FIELD, IMPORT
	}
	public NodeType type;
	public ASTNode astNode;
	public String comment;
	
	public ASTNodeWrapper(ASTNode node) {
		this.astNode = node;
		type = NodeType.NORMAL_CODE; 
	}
	
	public ASTNodeWrapper(ASTNode node, NodeType nodetype) {
		this.astNode = node;
		type = nodetype;
	}
	
	public ASTNodeWrapper(String comment) {
		this.comment = "// " + comment;
		type = NodeType.COMMENT;
	}

}
