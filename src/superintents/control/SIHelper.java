package superintents.control;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jdt.core.*;

public class SIHelper {
	private static String bogusString = "    /* INPUT:\n" + 
			"    THIS IS AN INTENT DESCRIPTION\n" + 
			"    OUTPUT:\n" + 
			"    superintents.impl.DataImpl@1cb8deef (MIMEType: THIS IS A MIME TYPE, value: THISISAVALUE)\n" + 
			"    */\n" + 
			"    Intent i = new Intent(THIS IS AN ACTION, THISISDATA, getContext(), java.lang.String.class);\n" + 
			"    i.setType(.mp3);\n" + 
			"     \n" + 
			"    i.addCategory(\"CATEGORY1\");\n" + 
			"    i.addCategory(\"CATEGORY2\");\n" + 
			"     \n" + 
			"    i.putExtra(\"SECOND URL\", YOUR TEXT HERE);\n" + 
			"    i.putExtra(\"THIRD URL\", YOUR TEXT HERE);\n" + 
			"    const int REQUEST_CODE= -1631654733;\n" + 
			"    startActivityForResult(i, REQUEST_CODE);\n" + 
			"     \n" + 
			"    @Override\n" + 
			"    public void onActivityResult(int requestCode, int resultCode, Intent data){\n" + 
			"            if(resultCode == REULST_OK && requestCode == REQUEST_CODE){\n" + 
			"                    //TODO: generated code\n" + 
			"            }\n" + 
			"    }\n" + 
			"";
	
	public static ITextEditor getEditor() {
		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow window = wb.getActiveWorkbenchWindow();
		IWorkbenchPage editor = window.getActivePage();
		IEditorPart part = editor.getActiveEditor();
		if (!(part instanceof AbstractTextEditor))
			return null;
		return (ITextEditor) part;
	}
	
	public static IJavaElement
	
	public static IDocument getDocument(ITextEditor editor) {
		IDocumentProvider dp = editor.getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		return doc;
	}
	
	public static int getCaretOffset(ITextEditor editor) {
		int offset = ((ITextSelection) editor.getSelectionProvider().getSelection()).getOffset();
		return offset;
	}
	
	public static void insertTestText(String testText) {
		ITextEditor editor = getEditor();
		
		try {
			getDocument(editor).replace(getCaretOffset(editor), 0, testText);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertBogusTest() {
		ITextEditor editor = getEditor();
		
		try {
			getDocument(editor).replace(getCaretOffset(editor), 0, bogusString);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
