package transformers;

import org.eclipse.jdt.core.dom.ASTNode;

public class ASTNodeWrapper {

	public ASTNode node;
	public int Offset;
	
	public ASTNodeWrapper(ASTNode node, int offset) {
		this.node = node;
		Offset = offset;
	}

}
