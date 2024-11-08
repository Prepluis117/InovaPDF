package Prepluis.InovaPDF;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.spire.doc.Document;
import com.spire.doc.ToPdfParameterList;
import com.spire.ms.System.Collections.Generic.List;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfDocumentBase;


import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;


public class Auxiliar {
	static String usuario=System.getProperty("user.name");
	static String ruta="C:\\Users\\"+usuario+"\\Desktop\\";
	public static java.util.List<File> obtener(DropTargetDropEvent evento) {
		java.util.List<File> ob=null;
		try {
			if(evento.getDropAction()==DnDConstants.ACTION_MOVE) {
				evento.acceptDrop(evento.getDropAction());
				final Transferable trans=evento.getTransferable();
				if(trans.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
					ob =(java.util.List<File>)trans.getTransferData(DataFlavor.javaFileListFlavor);
					evento.dropComplete(true);
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		return ob;
	}
	
	public static String rutas(FileInputStream archivos) {
		System.out.print(archivos);
		return null;
	}
	
	public static void convertir(FileInputStream archivos,String nombre) {
		PdfDocument pdf=new PdfDocument();
		pdf.loadFromStream(archivos);
		pdf.saveToFile(ruta+nombre+"(convertido).docx",FileFormat.DOCX);
		pdf.close();
		remplazo(ruta+nombre+"(convertido).docx");
	}
	
    public static void extraer(FileInputStream archivos,String nombre,String pagina1,String pagina2) {
    	System.out.print(nombre);
    	PdfDocument pdf1=new PdfDocument(), pdf2=new PdfDocument(); 
    	pdf1.loadFromStream(archivos);
    	pdf2.insertPageRange(pdf1, Integer.valueOf(pagina1)-1, Integer.valueOf(pagina2)-1);
    	//pdf2.insertPage(pdf1,Integer.valueOf(pagina)-1);
    	pdf2.saveToFile(ruta+nombre+"(convertido).pdf");
	}
	
	public static void unir(List<FileInputStream> archivos,String nombre) {
		FileInputStream[] inputStream = new FileInputStream[archivos.size()];
		for(int i=0; i<archivos.size();i++) {
			inputStream[i]=archivos.get(i);
			System.out.println(inputStream[i]);
		}
		PdfDocumentBase pdf=PdfDocument.mergeFiles(inputStream);
		pdf.save(ruta+nombre+"(convertido).pdf",FileFormat.PDF);
		pdf.close();
	}
	
	public static void remplazo(String nombre) {
		String texto1="Evaluation Warning : The document was created with Spire.PDF for java.";
	    String texto2="";
		try {
            FileInputStream entrada = new FileInputStream(nombre);
            XWPFDocument word = new XWPFDocument(entrada);
            for (XWPFParagraph i : word.getParagraphs()) {
                for (XWPFRun j : i.getRuns()) {
                    String texto = j.getText(0);
                    if (texto != null && texto.contains(texto1)) {
                        texto = texto.replace(texto1, texto2);
                        j.setText(texto, 0);
                    }
                }
    		}
            FileOutputStream salida = new FileOutputStream(nombre);
            word.write(salida);
            salida.close();
            entrada.close();
        } catch (Exception e) {
            System.out.println("Error al modificar el texto: " + e.getMessage());
        }
	}
}
