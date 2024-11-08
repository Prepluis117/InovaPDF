package Prepluis.InovaPDF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.EnumSet;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;
import com.spire.pdf.PdfPageBase;
//import com.spire.pdf.texts.PdfTextReplaceOptions;
//import com.spire.pdf.texts.PdfTextReplacer;
//import com.spire.pdf.texts.ReplaceActionType;



public class App {
  public static void main(String[] args) throws FileNotFoundException, IOException{
	  PdfDocument pdf1=new PdfDocument();
	  PdfDocument pdf3=new PdfDocument();
	  File fil1=new File("C:/Users/Mario/Desktop/Documentos_IINOVA/OPINION SAT 42.pdf");
	  File fil2=new File("C:/Users/Mario/Desktop/Documentos_IINOVA/TIIE.pdf");
	  FileInputStream inputStream1 = new FileInputStream(fil1);
	  FileInputStream[] inputStream2 = new FileInputStream[2];
	  inputStream2[0]=new FileInputStream(fil1);
	  inputStream2[1]=new FileInputStream(fil2);
	  //byte[] byteArray = new byte[(int) fil.length()];
	  /*try (FileInputStream inputStream = new FileInputStream(fil)) {
          inputStream.read(byteArray);
      }*/
	  //pdf.loadFromFile("C:/Users/Mario/Desktop/Documentos_IINOVA/OPINION SAT 42.pdf");
	  pdf1.loadFromStream(inputStream1);
	  pdf1.saveToFile("C:/Users/Mario/Desktop/Documentos_IINOVA/Prueba1.docx",FileFormat.DOCX);
	  pdf1.close();
	  
	  PdfDocumentBase pdf2=PdfDocument.mergeFiles(inputStream2);
	  pdf2.save("C:/Users/Mario/Desktop/Documentos_IINOVA/Prueba2.pdf",FileFormat.PDF);
	  pdf2.close();
	  
	  File fil3=new File("C:/Users/Mario/Desktop/Documentos_IINOVA/Prueba2.pdf");
	  FileInputStream inputStream3 = new FileInputStream(fil3);
	  pdf3.loadFromStream(inputStream3);
	  pdf3.split("C:/Users/Mario/Desktop/Documentos_IINOVA/Prueba3.pdf",0);
	  //pdf.loadFromBytes(byteArray);
	  
	  /*for(int i=0; i <pdf.getPages().getCount();i++) {
		  PdfPageBase page=pdf.getPages().get(i);
		  PdfTextReplacer replacer=new PdfTextReplacer(page);
		  PdfTextReplaceOptions options=new PdfTextReplaceOptions();
		  options.setReplaceType(EnumSet.of(ReplaceActionType.IgnoreCase));
		  replacer.replaceAllText("SAN","Hola");
	  }
	  pdf.saveToFile("C:/Users/Mario/Desktop/Documentos_IINOVA/Prueba.pdf");
	  pdf.dispose();
	  System.out.print("Hola");*/
	  
  }
}
