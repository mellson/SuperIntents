package superintents.control;

import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.ui.JavaUI;

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
	
	private static void InsertComment()
	{
		ITextEditor editor = (ITextEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		IEditorInput editorInput = editor.getEditorInput();
		IJavaElement elem = JavaUI.getEditorInputJavaElement(editorInput);
		if (elem instanceof ICompilationUnit) {
			ICompilationUnit unit = (ICompilationUnit) elem;
			CompilationUnit astRoot = parse(unit);
			
			//create a ASTRewrite
			AST ast = astRoot.getAST();
			ASTRewrite rewriter = ASTRewrite.create(ast);
	 
			//for getting insertion position
			TypeDeclaration typeDecl = (TypeDeclaration) astRoot.types().get(0);
			int offset = getSelectedMethod(typeDecl.getMethods());
			System.out.println("methodoffset = " + offset);
			MethodDeclaration methodDecl = typeDecl.getMethods()[offset];
			Block block = methodDecl.getBody();
	 
			ListRewrite listRewrite = rewriter.getListRewrite(block, Block.STATEMENTS_PROPERTY);
			Statement placeHolder = (Statement) rewriter.createStringPlaceholder("//mycomment", ASTNode.EMPTY_STATEMENT);
			listRewrite.insertFirst(placeHolder, null);
	 
			TextEdit edits = null;
			try {
				edits = rewriter.rewriteAST();
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			// apply the text edits to the compilation unit
			Document document = null;
			try {
				document = new Document(unit.getSource());
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			try {
				edits.apply(document);
			} catch (MalformedTreeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			// this is the code for adding statements
			try {
				unit.getBuffer().setContents(document.get());
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
			System.out.println("done");
		}
	}
	
	private static int getSelectedMethod(MethodDeclaration[] methods)
	{
		int result = 0;
		
		int caretOffset = getCaretOffset(getEditor());
		System.out.println(caretOffset);
		
		for (int i = 0; i < methods.length; i++) {
			if(caretOffset >= methods[i].getStartPosition() && caretOffset <= methods[i].getLength() + methods[i].getStartPosition())
				result = i;
		}
		
		return result;
	}
	
	private static void printSurroundingMethod() {
		ITextEditor editor = (ITextEditor) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();

		ITextSelection selection = (ITextSelection) editor
				.getSelectionProvider().getSelection();

		IEditorInput editorInput = editor.getEditorInput();
		IJavaElement elem = JavaUI.getEditorInputJavaElement(editorInput);
		if (elem instanceof ICompilationUnit) {
			ICompilationUnit unit = (ICompilationUnit) elem;
			IJavaElement selected = null;
			try {
				selected = unit.getElementAt(selection.getOffset());
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out
					.println("-----------------------------------------------------------------");
			System.out.println("selected=" + selected);
			System.out.println("selected.class=" + selected.getClass());

			try {
				printIMethods(unit);
			} catch (JavaModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out
					.println("-----------------------------------------------------------------");
		}
	}

	private static void printIMethods(ICompilationUnit unit)
			throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		for (IType type : allTypes) {
			printIMethodDetails(type);
		}
	}

	private static void printIMethodDetails(IType type)
			throws JavaModelException {
		IMethod[] methods = type.getMethods();
		for (IMethod method : methods) {

			System.out.println("Method name " + method.getElementName());
			System.out.println("Signature " + method.getSignature());
			System.out.println("Return Type " + method.getReturnType());

		}
	}

	protected static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS4);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit); // set source
		parser.setResolveBindings(true); // we need bindings later on
		return (CompilationUnit) parser.createAST(null /* IProgressMonitor */); // parse
	}

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
		
		//printSurroundingMethod(); //TESTING
		InsertComment(); //TESTING
		
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
