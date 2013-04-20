package superintents.util;

import java.util.ArrayList;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.jface.text.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import intentmodel.impl.*;
import transformers.Java2AST;

public class JDTInserter {
	public static void insertIntent(SuperIntentImpl intentImplementaion) {
		ArrayList<ASTNodeWrapper> nodeWrappers = Java2AST.transformSuperIntent(intentImplementaion);
		try {
			insertNodes(nodeWrappers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void insertNodes(ArrayList<ASTNodeWrapper> nodeWrappers) throws MalformedTreeException, BadLocationException, JavaModelException {
		// Initialize values
		ASTTupleHelper helper = JDTHelper.getASTTupleHelper();
		Block block = helper.currentMethod.getBody();
		int blockOffset = block.getStartPosition();
		int caretOffset = JDTHelper.getCaretOffset(helper.editor);
		int nodeStatementOffset = 0;
		int statementOffset = blockOffset;
		ASTNode nestedNode = null;
		ListRewrite listRewrite = null;

		// Check if the caret is placed in a nested block inside the current method
		for (Object o : block.statements())
			nestedNode = getDeepestNode((ASTNode) o, caretOffset);

		// If we are inside a nested block set that as the main block to insert into
		if (nestedNode != null)
			block = (Block) nestedNode;

		for (ASTNodeWrapper nodeWrapper : nodeWrappers) {
			// Get the offset where to insert the current node
			for (Object o : block.statements()) {
				statementOffset += o.toString().length();
				nodeStatementOffset += (statementOffset < caretOffset) ? 1 : 0;
			}
			CompilationUnit compilationUnit = (CompilationUnit) block.getRoot();
			switch (nodeWrapper.type) {
			case NORMAL_CODE:
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(nodeWrapper.astNode, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				break;
			case COMMENT:
				ASTNode commentNode = helper.rewriter.createStringPlaceholder(nodeWrapper.comment, ASTNode.EMPTY_STATEMENT);
				listRewrite = helper.rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
				listRewrite.insertAt(commentNode, nodeStatementOffset, null);
				nodeStatementOffset += 1;
				break;
			case CALLBACK_METHOD:
				TypeDeclaration typeDeclaration = (TypeDeclaration) compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(typeDeclaration, typeDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertAfter(nodeWrapper.astNode, helper.currentMethod, null);
				break;
			case FIELD:
				TypeDeclaration fieldDeclaration = (TypeDeclaration) compilationUnit.types().get(0);
				listRewrite = helper.rewriter.getListRewrite(fieldDeclaration, fieldDeclaration.getBodyDeclarationsProperty());
				listRewrite.insertFirst(nodeWrapper.astNode, null);
				break;
			case IMPORT:
				listRewrite = helper.rewriter.getListRewrite(compilationUnit, CompilationUnit.IMPORTS_PROPERTY);
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
	
	// This method will check whether the caret is placed inside the given statement and return the innermost ASTNode to insert new nodes in.
		private static ASTNode getDeepestNode(ASTNode astNode, int caretOffset) {
			ASTNode resultNode = null;
			if (astNode != null) {
				// find where the statements starts and stops
				int statementStart = astNode.getStartPosition();
				int statementEnd = statementStart + astNode.getLength();
				// if the caret is placed in a statement...
				if (caretOffset > statementStart && caretOffset < statementEnd) {
					// ..We check all of the properties
					for (Object structuralPropertyDescriptor : astNode.structuralPropertiesForType()) {
						Object structuralProperty = astNode.getStructuralProperty((StructuralPropertyDescriptor) structuralPropertyDescriptor);
						if (structuralProperty != null) {
							// If it is a block, we look further into the node
							if (structuralProperty.getClass() == Block.class) {
								ASTNode currentNode = (ASTNode) structuralProperty;
								int nodeStart = currentNode.getStartPosition();
								int nodeEnd = nodeStart + currentNode.getLength();
								// Is the caret inside this node, we recursively apply this method
								if (caretOffset > nodeStart && caretOffset < nodeEnd) {
									ASTNode node = getDeepestNode(currentNode, caretOffset);
									// If there was no block nested inside the current node, set the result to the current one
									if (node == null)
										resultNode = currentNode;
									else
										resultNode = node;
								}
							} else
								// If it is not a block return null
								resultNode = null;
						}
					}
				}
			}
			return resultNode;
		}
		
		private static boolean doesImportExist(CompilationUnit cu, String name)
		{
			ImportASTVisitor astv = new ImportASTVisitor(name);
			
			cu.accept(astv);
			
			return astv.getExists();
		}
}