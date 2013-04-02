package superintents.parsing;


import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import superintents.impl.*;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class XMLParser {
	
	public ArrayList<SuperIntentImpl> ParseXML(URL url) throws DocumentException
	{
		ArrayList<SuperIntentImpl> intents = new ArrayList<SuperIntentImpl>();
		
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		
		Element root = document.getRootElement();
		Iterator i = root.elementIterator();
		
		while (i.hasNext())
		{
			Element superintent = (Element) i.next();
			SuperIntentImpl newSI = new SuperIntentImpl();
			
			
			newSI.getIntent().setAction(superintent.attributeValue("action"));
		}
		
		
		//PARSEHERELOL
		
		return intents;
	}
}
