package superintents.parsing;


import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import superintents.impl.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class XMLParser {
	
	// Whoever cleans this up gets a lollypop.
	public ArrayList<SuperIntentImpl> ParseXML(URL url) throws DocumentException
	{
		DataImpl output = new DataImpl();
		String actionName = "";
		String description = "";
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<SuperIntentImpl> intents = new ArrayList<SuperIntentImpl>();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		
		Element root = document.getRootElement();
		Iterator i = root.elementIterator();
		
		while (i.hasNext())
		{
			Element superintent = (Element) i.next();
			
			actionName =superintent.attributeValue("action");
			description = superintent.attributeValue("description");
			
			// Add categories
			Element cats = superintent.element("categories");
			Iterator catIterator = cats.elementIterator();
			while (catIterator.hasNext())
			{
				Element cat = (Element) catIterator.next();
				categories.add(cat.attributeValue("value"));
			}
			
			Element op = (Element) superintent.element("output");
			output.setMIMEType(op.attributeValue("MIMEType"));
			output.setValue(URI.create(op.attributeValue("value")));
			
			Element inputs = (Element) superintent.element("input");
			Iterator inputIterator = inputs.elementIterator();
			while (inputIterator.hasNext())
			{
				Element input = (Element) inputIterator.next();
				DataImpl dataInput = new DataImpl();
				dataInput.setMIMEType(input.attributeValue("MIMEType"));
				dataInput.setValue(URI.create(input.attributeValue("value")));
				
				intents.add(getSuperIntent(dataInput, actionName, description, output, categories));
			}
			
		}
		
		
		//PARSEHERELOL
		
		return intents;
	}
	
	public SuperIntentImpl getSuperIntent(DataImpl input, String actionName, String description, DataImpl output, ArrayList<String> categories)
	{
		
		SuperIntentImpl si = new SuperIntentImpl();
		IntentImpl in = new IntentImpl();
		
		in.setAction(actionName); // set action
		si.setDescription(description);
		
		in.getCategories().addAll(categories); // Add categories
		in.setData(input); // set input
		si.setIntent(in); // set intent
		
		si.setOutput(output); // set output
		
		return si;
		
	}
}
