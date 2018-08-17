package main;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class GUIBrowse extends JFrame implements ActionListener
{
	private JPanel pBase;
	private JPanel pMenu;
	private JButton btLoad;
	private JButton btPlay;
	private JButton btReset;
	private JTextArea tfCode;
	private JTextArea tfMsgs;
	private JScrollPane spCode;
	private JScrollPane spMsgs;

	public GUIBrowse()
	{
		super("Compiladores");
		this.setLocation(400,  200);
		pBase = new JPanel();
		pBase.setLayout(new BoxLayout(pBase,BoxLayout.PAGE_AXIS ));
		pMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));

		ImageIcon load = convertImage("/img/open.png");
		ImageIcon run = convertImage("/img/play.png");
		ImageIcon reset = convertImage("/img/reset.png");
		
		btLoad = new JButton(load);
		btLoad.addActionListener(this);
		btPlay = new JButton(run);
		btPlay.addActionListener(this);
		btReset = new JButton(reset);
		btReset.addActionListener(this);

		tfCode = new JTextArea("");
		tfCode.setEditable(true);
		spCode = new JScrollPane(tfCode);
		tfMsgs = new JTextArea("");
		tfMsgs.setBackground(Color.DARK_GRAY);
		
		spMsgs = new JScrollPane(tfMsgs);
	

		pMenu.add(btLoad);
		pMenu.add(btPlay);
		pMenu.add(btReset);
		
		pBase.add(pMenu);
		pBase.add(spCode);
		pBase.add(new JLabel(" "));
		pBase.add(spMsgs);

		add(pBase);
		pack();

	}
	
	private ImageIcon convertImage(String path){
		ImageIcon origen = new ImageIcon(getClass().getResource(path));
		Image conversion = origen.getImage();
		Image tam = conversion.getScaledInstance(20, 15, Image.SCALE_SMOOTH);
		ImageIcon fin = new ImageIcon(tam);	
		
		return fin;
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btLoad)
		{
			JFileChooser selectorArchivos = new JFileChooser();
			selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int resultado = selectorArchivos.showOpenDialog(this);
			File source = selectorArchivos.getSelectedFile(); // obtiene el archivo seleccionado
			
			if(resultado == JFileChooser.APPROVE_OPTION){
				try(FileReader fr =new FileReader(source)){
			        String cadena="";
			        int valor=fr.read();
			        while(valor!=-1){
			            cadena=cadena+(char)valor;
			            valor=fr.read();
			        }
			        tfCode.setText(cadena);
			    } catch (IOException e1) {
			        e1.printStackTrace();
			    }
			}
			
		}
		
		if(e.getSource() == btPlay)
		{	
			String cad = tfCode.getText();
			Interprete in = new Interprete(cad);
			in.analize();
			
			//in.checaPalarbas();
			//in.checaParametros();
			
			

		}
		if(e.getSource() == btReset)
		{
			tfCode.setText("");
			tfMsgs.setText("");
			
		}
	}
	

	public static void main(String [] args)
	{
		GUIBrowse guI = new GUIBrowse();
		guI.setVisible(true);
		guI.setSize(400, 350);
		guI.setDefaultCloseOperation(guI.EXIT_ON_CLOSE);
	}
}
