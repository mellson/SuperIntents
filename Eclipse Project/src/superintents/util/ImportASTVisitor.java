package superintents.util;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ImportDeclaration;

public class ImportASTVisitor extends ASTVisitor{
		
		boolean importExists = false;
		String name;
		
		public ImportASTVisitor(String name) {
			this.name =  name;
		}
		
		public boolean visit(ImportDeclaration node) {
			if (node.getName().toString().equals(name))
				importExists = true;
			return false; 
		}
		
		public boolean getExists()
		{
			return importExists;
		}
}
