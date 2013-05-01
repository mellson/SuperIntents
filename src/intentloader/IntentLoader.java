package intentloader;

import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Map;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import intentmodel.IntentmodelPackage;
import intentmodel.impl.SuperIntentImpl;

public class IntentLoader {
	public static ArrayList<String> loadListOfIntentFilesFromJar() {
		// Initialize the model
		IntentmodelPackage.eINSTANCE.eClass();
		ArrayList<String> output = new ArrayList<String>();
		try {
			// Locate the jar file of this plug-in
			CodeSource src = IntentLoader.class.getProtectionDomain().getCodeSource();
			if (src != null) {
				URL jar = src.getLocation();
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				ZipEntry ze = null;
				while ((ze = zip.getNextEntry()) != null) {
					if (ze.getName().startsWith("resources/") && ze.getName().endsWith(".intentmodel")) {
						output.add(ze.getName().replace("resources/", "").replace(".intentmodel", ""));
					}
				}

			}
		} catch (Exception e) {
		}
		return output;
	}

	public static SuperIntentImpl loadIntentInstanceFromJar(String filename) {
		// Initialize the model
		IntentmodelPackage.eINSTANCE.eClass();
		SuperIntentImpl ss = null;
		try {
			// Locate the jar file of this plug-in
			CodeSource src = IntentLoader.class.getProtectionDomain().getCodeSource();
			if (src != null) {
				URL jar = src.getLocation();
				ZipInputStream zip = new ZipInputStream(jar.openStream());
				ZipEntry ze = null;
				while ((ze = zip.getNextEntry()) != null) {
						if (ze.getName().contains(filename) && ze.getName().endsWith(".intentmodel")) {
						// Copy the input stream
						byte[] buffer = new byte[(int)ze.getSize()];
					    while (zip.available() > 0) {
					    	zip.read(buffer, 0, buffer.length);
					    }
					    InputStream is = new ByteArrayInputStream(buffer);
						
					    // Create a new SuperIntentImpl from the input stream 
						Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
						Map<String, Object> m = reg.getExtensionToFactoryMap();
						m.put("intentmodel", new XMIResourceFactoryImpl());
						XMIResourceImpl xi = new XMIResourceImpl();
						xi.load(is, m);
						ss = (SuperIntentImpl) xi.getContents().get(0);
						
						zip.close();
						return ss;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ss;
	}
	
	// These two methods below are used when running the plugin from inside Eclipse - when installed as a plug-in use the two methods above
	public static ArrayList<String> loadListOfIntentFilesFromEclipse() {
		URL url;
		ArrayList<String> output = new ArrayList<String>();
		try {
			url = new URL("platform:/plugin/SuperIntents/src/resources");
			File f = new File(FileLocator.resolve(url).toURI());
			ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
			for (String e : names) {
				output.add(e.replace(".intentmodel", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	public static SuperIntentImpl loadIntentInstanceFromEclipse(String filename) {
		// Initialize the model
		IntentmodelPackage.eINSTANCE.eClass();

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		
		// Get the resource
		Resource resource = resSet.getResource(URI.createURI((IntentLoader.class.getResource("/resources/" + filename + ".intentmodel")).toString()),true);
		
		// Cast resource to SuperIntent
		SuperIntentImpl superintent = (SuperIntentImpl) resource.getContents().get(0);
		
		return superintent;
	}
}