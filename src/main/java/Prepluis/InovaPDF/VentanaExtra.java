package Prepluis.InovaPDF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.spire.ms.System.Collections.Generic.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VentanaExtra extends JFrame implements ActionListener,MouseListener, WindowListener{
	private JPanel p1;
	private JList l1;
	private JScrollPane s1;
	private JButton b1;
	private JTextField t1;
	
	private File ruta;
	private String cadena;
	private String nombre;
	private String[] archivos;
	private List<FileInputStream> datas=new List<FileInputStream>();
	
	public VentanaExtra() {
		cadena="C:/";
		ruta=new File(cadena);
		archivos=ruta.list();
		this.setSize(500,300);
		this.setResizable(false); 
		this.setTitle("Buscar");
		this.addWindowListener(this);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		componentes();
		botones();
		textos();
	}
	private void componentes() {
		p1=new JPanel();
	    this.getContentPane().add(p1);
		p1.setLayout(null);
		l1=new JList(archivos);
		l1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		l1.addMouseListener(this);
		s1=new JScrollPane(l1);
		s1.setBounds(20,20,440,180);
		p1.add(s1);
	}
	private void botones() {
		b1=new JButton("<-");
		b1.setBounds(20,210,50,20);
		p1.add(b1);
		b1.addActionListener(this);
		b1=new JButton("Ok");
		b1.setBounds(410,210,50,20);
		p1.add(b1);
		b1.addActionListener(this);
	}
	private void textos() {
		t1=new JTextField();
		t1.setBounds(80,210,320,20);
		p1.add(t1);
		t1.setText(cadena);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="<-" && cadena.length()>3) {
			String [] otra=cadena.split("/");
			cadena="";
			for(int i=0;i<otra.length-1;i++) {
				cadena=cadena+otra[i]+"/";
			}
			ruta=new File(cadena);
			t1.setText(cadena);
			archivos=ruta.list();
			l1.setListData(archivos);
		}else if(e.getActionCommand()=="Ok") {
			this.setVisible(false);
			Ventana1 v=new Ventana1(datas,nombre);
		    v.setVisible(true);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		try {
			if(new File(cadena+l1.getSelectedValue()+"/").isDirectory()) {
			//if(!new File(cadena+l1.getSelectedValue()+"/").isFile() && new File(cadena+l1.getSelectedValue()+"/").list().length>0) {
				cadena=cadena+l1.getSelectedValue()+"/";
				System.out.println(cadena);
			    ruta=new File(cadena);
			    t1.setText(cadena);
			    archivos=ruta.list();
			    l1.setListData(archivos);
		    }else {
			    ruta=new File(cadena+l1.getSelectedValue());
			    nombre=ruta.getName().substring(0,ruta.getName().length()-4);
			    FileInputStream data = new FileInputStream(ruta);
				datas.add(data);
		    }
		}
		catch (Exception e1) {
		}
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void windowOpened(WindowEvent e) {
	}
	public void windowClosing(WindowEvent e) {
		Ventana1 v=new Ventana1(datas,nombre);
	    v.setVisible(true);
	}
	public void windowClosed(WindowEvent e) {
	}
	public void windowIconified(WindowEvent e) {
	}
	public void windowDeiconified(WindowEvent e) {
	}
	public void windowActivated(WindowEvent e) {
	}
	public void windowDeactivated(WindowEvent e) {
	}
}

