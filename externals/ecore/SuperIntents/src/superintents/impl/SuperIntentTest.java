package superintents.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.eclipse.emf.common.util.BasicEList;

import superintents.Intent;

public class SuperIntentTest {
	
	public int requestcode;
	
	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
			SuperIntentTest lollerskates = new SuperIntentTest();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SuperIntentTest() throws URISyntaxException {
		SuperIntentImpl bigRedButtonIntent = new SuperIntentImpl();
		bigRedButtonIntent.setDescription("THIS IS AN INTENT DESCRIPTION");

		DataImpl bigRedButtonOutput = new DataImpl();
		bigRedButtonOutput.setMIMEType("THIS IS A MIME TYPE");
		bigRedButtonOutput.setValue(new java.net.URI("THISISAVALUE"));
		bigRedButtonIntent.setOutput(bigRedButtonOutput);

		IntentImpl newIntent = new IntentImpl();
		newIntent.setAction("THIS IS AN ACTION");
		
		newIntent.categories = new BasicEList<String>();
		newIntent.categories.add("CATEGORY1");
		newIntent.categories.add("CATEGORY2");
		
		newIntent.setComponent(String.class);
		
		DataImpl data = new DataImpl();
		data.value = URI.create("THISISDATA");
		data.mimeType = ".mp3";
		newIntent.setData(data);
		
		bigRedButtonIntent.setIntent(newIntent);
		
		bigRedButtonIntent.intent.getExtras().put("SECOND URL", "YOUR TEXT HERE");
		bigRedButtonIntent.intent.getExtras().put("THIRD URL", "YOUR TEXT HERE");
		
		System.out.println(M2T(bigRedButtonIntent));

	}

	public String M2T(SuperIntentImpl sIntent) {
		String returnString = "";

		// comments
		returnString += sIntent.description != null ? "/* INPUT: \n"
				+ sIntent.description + "\n" : "";
		returnString += sIntent.output != null ? "OUTPUT: \n"
				+ sIntent.output.toString() + "\n*/\n" : "";

		// create intent
		returnString += "Intent i =";

		// Intent(String action, Uri uri, Context packageContext, Class<?> cls)
		if (sIntent.intent.getAction() != null
				&& sIntent.intent.getComponent() != null
				&& sIntent.intent.getData().getValue() != null) {
			returnString += " new Intent(" + sIntent.intent.getAction() + ", "
					+ sIntent.intent.getData().getValue() + ", "
					+ "getContext()" + ", " 
					+ sIntent.intent.getComponent().toString().replace("class ", "") + ".class);\n";
			returnString += generateDataType(sIntent.intent);
		}

		// Intent(Context packageContext, Class<?> cls)
		else if (sIntent.intent.getComponent() != null) {
			returnString += " new Intent(getContext(), " + sIntent.intent.getComponent().toString().replace("class ", "") + ".class);\n";
			returnString += generateData(sIntent.intent);
			returnString += generateDataType(sIntent.intent);
			}

		// Intent(String action, Uri uri)
		else if (sIntent.intent.getAction() != null
				&& sIntent.intent.getData() != null) {
			returnString += " new Intent(" + sIntent.intent.getAction() + ", "
					+ sIntent.intent.getData().getValue() + ");\n";
			returnString += generateDataType(sIntent.intent);
		}

		// Intent(String action)
		else if (sIntent.intent.getAction() != null) {
			returnString += " new Intent(" + sIntent.intent.getAction() + ");\n";
			returnString += generateData(sIntent.intent);
			returnString += generateDataType(sIntent.intent);
			}

		returnString += "\n" + generateCategories(sIntent.intent);
		
		returnString += "\n" + generateExtras(sIntent.intent);
		
		returnString += generateStartActivityMethod(sIntent);
		
		returnString += generateCallbackMethod(sIntent.intent);
		
		return returnString;
	}
	
	public String generateCategories(Intent i)
	{
		String returnString = "";
		//set category
		if(i.getCategories() != null)
			for (String cat : i.getCategories()) {
				returnString += "i.addCategory(\"" + cat +"\");\n";
			}
		return returnString;
	}
	
	public String generateData(Intent i)
	{
		String returnString = "";
		
		if(i.getData().getValue() != null)
			returnString += "i.setData(" + i.getData().getValue() + ");\n";
		
		return returnString;
	}
	
	public String generateDataType(Intent i)
	{
		String returnString = "";
		
		if(i.getData().getMIMEType() != null)
			returnString += "i.setType(" + i.getData().getMIMEType() + ");\n";
		
		return returnString;
	}
	
	public String generateExtras(Intent i)
	{
		String returnString = "";
		
		for (String extra : i.getExtras().keySet()) {
			returnString += "i.putExtra(\""+ extra +"\", " + i.getExtras().get(extra) + ");\n";
		}
		
		return returnString;
	}
	
	public String generateStartActivityMethod(SuperIntentImpl i)
	{
		String returnString = "";
		
		if(i.output == null)
			returnString += "startActivity(i);\n";
		else{
			Random rand = new Random(Integer.MAX_VALUE);
			requestcode = rand.nextInt();
			returnString += "const int REQUEST_CODE= " + requestcode + ";\n";
			returnString += "startActivityForResult(i, REQUEST_CODE);\n";
		}
		
		return returnString;	

	}
	
	public String generateCallbackMethod(Intent i)
	{
		String returnString = "";
		
		returnString += "\n@Override\n" + 
						"public void onActivityResult(int requestCode, int resultCode, Intent data){\n" +
						"	if(resultCode == REULST_OK && requestCode == REQUEST_CODE){\n" +
						"		//TODO: generated code\n" +
						"	}\n" + 
						"}\n";
		return returnString;	

	}
}
