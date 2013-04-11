package transformers;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;

import superintents.SuperIntent;
import superintents.impl.*;;

public class M2MJava2AST {

	public SuperIntent createTestSI() {
		SuperIntentImpl bigRedButtonIntent = new SuperIntentImpl();
		bigRedButtonIntent.setDescription("THIS IS AN INTENT DESCRIPTION");

		DataImpl bigRedButtonOutput = new DataImpl();
		bigRedButtonOutput.setMIMEType("THIS IS A MIME TYPE");
		try {
			bigRedButtonOutput.setValue(new java.net.URI("THISISAVALUE"));
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bigRedButtonIntent.setOutput(bigRedButtonOutput);

		IntentImpl newIntent = new IntentImpl();
		newIntent.setAction("THIS IS AN ACTION");
		
		newIntent.getCategories().add("CATEGORY1");
		newIntent.getCategories().add("CATEGORY2");
		
		newIntent.setComponent(String.class);
		
		DataImpl data = new DataImpl();
		data.setValue(URI.create("THISISDATA"));
		data.setMIMEType(".mp3");
		newIntent.setData(data);
		
		bigRedButtonIntent.setIntent(newIntent);
		
		bigRedButtonIntent.getIntent().getExtras().put("SECOND URL", "YOUR TEXT HERE");
		bigRedButtonIntent.getIntent().getExtras().put("THIRD URL", "YOUR TEXT HERE");
		
		return bigRedButtonIntent;
	}
	
	public ASTNode transformSuperIntent(SuperIntent si, AST ast, ASTRewrite rewriter)
	{
		return null;
	}
	
	public ASTNode InsertComment(String comment, AST ast, ASTRewrite rewriter)
	{
		
		/*Statement placeHolder = (Statement) rewriter.createStringPlaceholder("//mycomment", ASTNode.EMPTY_STATEMENT);
		rewriter.cre
		rewriter.insertFirst(placeHolder, null);*/
		return null;
	}
}
	