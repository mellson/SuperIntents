package superintents.util;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;

public class BlockASTVisitor extends ASTVisitor {

	Block block = null;
	int caretOffset = 0;

	public BlockASTVisitor(int caretOffset) {
		this.caretOffset = caretOffset;
	}

	public boolean visit(Block node) {
		if (node.getStartPosition() < caretOffset && caretOffset < (node.getStartPosition() + node.getLength())) {
			if (block == null)
				block = node;
			else if (node.getStartPosition() > block.getStartPosition())
				block = node;
		}
		return true;
	}

	public Block getBlock() {
		return block;
	}
}
