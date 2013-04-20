package superintents.util;

import java.util.ArrayList;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.jface.text.*;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import intentmodel.impl.*;

public class JDTInserter {
	public static void insertIntent(SuperIntentImpl intentImplementaion) {
		ArrayList<ASTNodeWrapper> nodeWrappers = Java2AST.transformSuperIntent(intentImplementaion);
		try {
			insertNodes(nodeWrappers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static int insertionOffset(Block block, int nodeStatementOffset, int caretOffset) {
		int statementOffset = block.getStartPosition();
		// Get the offset where to insert the current node
		for (Object o : block.statements()) {
			statementOffset += o.toString().length();
			nodeStatementOffset += (statementOffset < caretOffset) ? 1 : 0;
		}
		return nodeStatementOffset;
	}

	private static void insertNodes(ArrayList<ASTNodeWrapper> nodeWrappers) throws Exception {
		// Initialize values
		ASTTupleHelper helper = JDTHelper.getASTTupleHelper();
		int caretOffset = JDTHelper.getCaretOffset(helper.editor);
		Block block = getDeepestBlock(helper.compilationUnit, caretOffset);
		// TODO Convert exception to popup window
		if (block == null)
			throw new Exception("Exception, you need to be inside the curly braces of a method to insert an intent.");
		int nodeStatementOffset = 0;
		ListRewrite listRewrite = null;
		for (ASTNodeWrapper nodeWrapper : nodeWrappers) {
			switch (nodeWrapper.type) {
			case NORMAL_CODE:
				nodeStatementOffset = insertionOffset(block, nodeStatementOffset, caretOffset);
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(nodeWrapper.astNode, nodeStatementOffset, null);
				nodeStatementOffset++;
				break;
			case COMMENT:
				ASTNode commentNode = helper.rewriter.createStringPlaceholder(nodeWrapper.comment, ASTNode.EMPTY_STATEMENT);
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(commentNode, nodeStatementOffset, null);
				nodeStatementOffset++;
				break;
			case CALLBACK_METHOD:
				if (nodeWrapper.existingCallbackMethod != null) {
					listRewrite = helper.rewriter.getListRewrite(nodeWrapper.existingCallbackMethod, Block.STATEMENTS_PROPERTY);
					listRewrite.insertAt(nodeWrapper.astNode, nodeStatementOffset, null);
					nodeStatementOffset++;
					break;
				} else {
					TypeDeclaration typeDeclaration = (TypeDeclaration) helper.compilationUnit.types().get(0);
					listRewrite = helper.rewriter.getListRewrite(typeDeclaration, typeDeclaration.getBodyDeclarationsProperty());
					listRewrite.insertAfter(nodeWrapper.astNode, helper.currentMethod, null);
					break;
				}
			case FIELD:
				TypeDeclaration fieldDeclaration = (TypeDeclaration) helper.compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(fieldDeclaration, fieldDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertFirst(nodeWrapper.astNode, null);
				break;
			case IMPORT:
				listRewrite = helper.rewriter.getListRewrite(helper.compilationUnit, CompilationUnit.IMPORTS_PROPERTY);
				listRewrite.insertFirst(nodeWrapper.astNode, null);
				break;
			default:
				break;
			}
		}

		TextEdit edits = helper.rewriter.rewriteAST();
		Document document = new Document(helper.unit.getSource());
		edits.apply(document);
		helper.unit.getBuffer().setContents(document.get());

		// Jump to the position right after the inserted node and focus the editor
		helper.editor.selectAndReveal(edits.getExclusiveEnd(), 0);
		helper.editor.setFocus();
	}

	public static Block getDeepestBlock(CompilationUnit cu, int caretOffset) {
		BlockASTVisitor astv = new BlockASTVisitor(caretOffset);
		cu.accept(astv);
		return astv.getBlock();
	}
}