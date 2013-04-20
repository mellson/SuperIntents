package emfloader;

import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import com.sun.org.apache.bcel.internal.generic.LoadInstruction;

import intentmodel.IntentmodelPackage;
import intentmodel.SuperIntent;
import intentmodel.Data;
import intentmodel.Intent;
import intentmodel.StringToObject;
import intentmodel.impl.SuperIntentImpl;

public class EMFLoadModel {

	
		public ArrayList<String> loadListOfIntentFiles(String path){
			CodeSource src = EMFLoadModel.class.getProtectionDomain().getCodeSource();
			URL jar = src.getLocation();
			path = jar.toString();
			path = path.replace("file:", "");
			path = path + "src/resources";
			File f = new File(path);
			ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
			ArrayList<String> output = new ArrayList<String>();
			
			for(String e : names){
				output.add(e.replace(".intentmodel", ""));
			}
			
			return output;
		}
		
		public SuperIntentImpl loadIntentInstance(String filename) {
			 // Initialize the model
		    System.out.println("Trying to load file: " + filename);
		    IntentmodelPackage.eINSTANCE.eClass();
		    
		    // Register the XMI resource factory for the .intentmodel extension
		    Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		    Map<String, Object> m = reg.getExtensionToFactoryMap();
		    m.put("intentmodel", new XMIResourceFactoryImpl());

		    // Obtain a new resource set
		    ResourceSet resSet = new ResourceSetImpl();
		    
		    // Get the resource
		    Resource resource = resSet.getResource(URI.createURI((EMFLoadModel.class.getResource("/resources/"+filename+".intentmodel")).toString()), true);
		    
		    //Cast resource to SuperIntent
		    SuperIntentImpl superintent = (SuperIntentImpl) resource.getContents().get(0);
		    
		    System.out.println("Loaded intent: "+ superintent.getDescription());
		    
		    return superintent;
		}
}