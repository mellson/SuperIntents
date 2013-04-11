package transformers;

import java.util.ArrayList;
import org.eclipse.jdt.core.dom.*;
import intentmodel.impl.*;

public class Java2AST {

	public static SuperIntentImpl createTestSI() {
		SuperIntentImpl bigRedButtonIntent = new SuperIntentImpl();
		bigRedButtonIntent.setDescription("THIS IS AN INTENT DESCRIPTION");

		DataImpl bigRedButtonOutput = new DataImpl();
		bigRedButtonOutput.setMIMEType("THIS IS A MIME TYPE");
		bigRedButtonOutput.setValue("THISISAVALUE");
		
		bigRedButtonIntent.setOutput(bigRedButtonOutput);
		
		IntentImpl newIntent = new IntentImpl();
		newIntent.setAction("THIS IS AN ACTION");
		
		newIntent.getCategories().add("CATEGORY1");
		newIntent.getCategories().add("CATEGORY2");
		
		newIntent.setComponent("String");
		
		DataImpl data = new DataImpl();
		data.setValue("THISISDATA");
		data.setMIMEType(".mp3");
		newIntent.setData(data);
		
		bigRedButtonIntent.setIntent(newIntent);
		
		//bigRedButtonIntent.getIntent().getExtras().put("SECOND URL", "YOUR TEXT HERE");
		//bigRedButtonIntent.getIntent().getExtras().put("THIRD URL", "YOUR TEXT HERE");
		
		return bigRedButtonIntent;
	}
	
	
	public static ArrayList<ASTNodeWrapper> transformSuperIntent(SuperIntentImpl si)
	{
		//result list
		ArrayList<ASTNodeWrapper> resultList = new ArrayList<ASTNodeWrapper>();
		
		//AST for generating nodes
		AST ast = AST.newAST(AST.JLS4);
		resultList.add(initializeIntent(si, ast));
		
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public static ASTNodeWrapper initializeIntent(SuperIntentImpl si, AST ast)
	{
		//set the name of the variable
		VariableDeclarationFragment vdf = ast.newVariableDeclarationFragment();
		vdf.setName(ast.newSimpleName("i"));
		
		//set the class of the instance 
		ClassInstanceCreation cic = ast.newClassInstanceCreation();
		cic.setType(ast.newSimpleType(ast.newSimpleName("Intent")));
		
		//set arguments
		StringLiteral arg1 = ast.newStringLiteral();
		arg1.setLiteralValue(si.getIntent().getAction());
		cic.arguments().add(arg1);
		
		StringLiteral arg2 = ast.newStringLiteral();
		arg2.setLiteralValue(si.getIntent().getCategories().get(0));
		cic.arguments().add(arg2);
		
		TypeLiteral arg3 = ast.newTypeLiteral();
		arg3.setType(ast.newSimpleType(ast.newSimpleName(si.getIntent().getComponent())));
		cic.arguments().add(arg3);
		
		vdf.setInitializer(cic);
		
		//set the type of the variable
		FieldDeclaration f = ast.newFieldDeclaration(vdf);
		f.setType(ast.newSimpleType(ast.newSimpleName("Intent")));
		
		ASTNodeWrapper wrapper = new ASTNodeWrapper(f, 0);
		return wrapper;
	}
	
}
	