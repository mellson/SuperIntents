package transformers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.*;

import superintents.impl.*;;

public class M2MJava2AST {

	public static SuperIntentImpl createTestSI() {
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
	
	
	public static ArrayList<ASTNodeWrapper> transformSuperIntent(SuperIntentImpl si, AST ast)
	{
		ArrayList<ASTNodeWrapper> resultList = new ArrayList<ASTNodeWrapper>();
		
		return resultList;
	}
	
	public ASTNode initializeIntent(SuperIntentImpl si, AST ast)
	{
		return null;
	}
	
}
	