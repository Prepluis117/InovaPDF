package Prepluis.InovaPDF;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.spire.ms.System.Collections.Generic.List;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

//import Paquete.VentanaExtra;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;

public class Ventana1 extends JFrame implements ActionListener,DropTargetListener{
	private int n=0;
	private String nombre;
	private JLabel l1, l2;
    private JTextField t1, t2;
	private JButton b1, b2, b3, b4, b5;
	private JPanel p1;
	private List<FileInputStream> archivos=new List<FileInputStream>();
	
	
	public Ventana1(){
		this.setSize(500,300);
		this.setResizable(false); 
		this.setTitle("Ventana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentes();
	}
	
	public Ventana1(List<FileInputStream> archivos,String nombre){
		this.archivos=archivos;
		this.nombre=nombre;
		System.out.print(this.archivos.size());
		this.setSize(500,300);
		this.setResizable(false); 
		this.setTitle("Ventana");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentes();
	}
	
	private void componentes(){
	    p1=new JPanel();
	    this.getContentPane().add(p1);
		p1.setLayout(null);
		textos(p1);
		botones(p1);
	}
	private void textos(JPanel p1){
		Border blackline = BorderFactory.createLineBorder(Color.black);
		l1=new JLabel();
		l1.setBounds(20,20,200,200);
		l1.setBorder(blackline);
		l1.setDropTarget(getDropTarget());
        new DropTarget(l1,this);
		p1.add(l1);
		
		t1=new JTextField();
		t1.setBounds(360,60,40,20);
		p1.add(t1);
		t1.setEnabled(false);
		
		t2=new JTextField();
		t2.setBounds(420,60,40,20);
		p1.add(t2);
		t2.setEnabled(false);
	}
	private void botones(JPanel p1) {
		b1=new JButton("Convertir a word");
		b1.setBounds(260,20,200,20);
		p1.add(b1);
		b1.addActionListener(this);
		b1.setEnabled(false);
		
		b2=new JButton("Extraer");
		b2.setBounds(260,60,80,20);
		p1.add(b2);
		b2.addActionListener(this);
		b2.setEnabled(false);
		
		b3=new JButton("Unir PDFs");
		b3.setBounds(260,100,200,20);
		p1.add(b3);
		b3.addActionListener(this);
		b3.setEnabled(false);
		
		b4=new JButton("Borrar");
		b4.setBounds(260,140,200,20);
		p1.add(b4);
		b4.addActionListener(this);
		b4.setEnabled(false);
		
		b5=new JButton("Buscar por ruta");
		b5.setBounds(260,180,200,20);
		p1.add(b5);
		b5.addActionListener(this);
		//b5.setEnabled(false);
		if(archivos.size()>1) {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(true);
			b4.setEnabled(true);
			t1.setEnabled(false);
			t2.setEnabled(false);
		}else if(archivos.size()==1) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(false);
			b4.setEnabled(true);
			t1.setEnabled(true);
			t2.setEnabled(true);
		}else {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			t1.setEnabled(false);
			t2.setEnabled(false);
		}

	}
	public void actionPerformed(ActionEvent e) {	
		if(e.getActionCommand()=="Convertir a word") {
			Auxiliar.convertir(archivos.get(0),nombre);
		}else if(e.getActionCommand()=="Extraer") {
			Auxiliar.extraer(archivos.get(0),nombre,t1.getText(),t2.getText());
		}else if(e.getActionCommand()=="Unir PDFs") {
			Auxiliar.unir(archivos,nombre);
		}else if(e.getActionCommand()=="Borrar") {
			archivos.clear();
		}
		else if(e.getActionCommand()=="Buscar por ruta") {
			this.setVisible(false);
			VentanaExtra v=new VentanaExtra();
		    v.setVisible(true);
		}
		archivos.clear();
		t1.setText("");
		t2.setText("");
		l1.setText("");
		if(archivos.size()==0) {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			t1.setEnabled(false);
			t2.setEnabled(false);
		}
	}
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dragExit(DropTargetEvent dte) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		String bre=null;
		java.util.List<File>ob=Auxiliar.obtener(dtde);
		if(ob.get(0).getAbsolutePath().substring(ob.get(0).getAbsolutePath().lastIndexOf(".")+1).compareTo("pdf")==0 || ob.get(0).getAbsolutePath().substring(ob.get(0).getAbsolutePath().lastIndexOf(".")+1).compareTo("PDF")==0) {
			try {
				archivos.add(new FileInputStream(ob.get(0)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			nombre=ob.get(0).getName().substring(0,ob.get(0).getName().length()-4);
			System.out.print(nombre);
		}
		else {
			JOptionPane.showMessageDialog(null,"Solo se admite extension PDF");
		}
		l1.setText(l1.getText()+nombre);
		if(archivos.size()>1) {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(true);
			b4.setEnabled(true);
			t1.setEnabled(false);
			t2.setEnabled(false);
		}else if(archivos.size()==1) {
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(false);
			b4.setEnabled(true);
			t1.setEnabled(true);
			t2.setEnabled(true);
		}else {
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			t1.setEnabled(false);
			t2.setEnabled(false);
		}
	}
}
