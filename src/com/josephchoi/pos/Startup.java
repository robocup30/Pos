package com.josephchoi.pos;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;

import javax.print.*;
import javax.swing.*;

public class Startup extends JFrame{
	
	FlowLayout flo;
	BorderLayout border;
	
	String filePath;
	
	MenuBar menuBarPanel = new MenuBar(this);
	
	KeyPadPanel keyPadPanel;
	PasswordPanel passwordPanel;
	MenuPanel menuPanel;
	ChangeMenuPanel changeMenuPanel;
	StatisticPanel statisticPanel;
	ChangePasswordPanel changePasswordPanel;
	
	JButton test = new JButton("Test");
	JPanel startupPanel;
	
	public Startup(){
		
		super("Pine Sushi Square POS");
		setLookAndFeel();
		setExtendedState(MAXIMIZED_BOTH);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		flo = new FlowLayout();
		border = new BorderLayout();
		setLayout(flo);
		setJMenuBar(null);
		startupPanel = new JPanel();
		startupPanel.setLayout(flo);
		startupPanel.setPreferredSize(new Dimension(10000, 1000));
		
		String where = System.getenv("ProgramFiles");
		
		File here = new File(where + "\\POSlogs");
		here.mkdir();
		File there = new File(where + "\\POSlogs\\sales logs");
		there.mkdir();
		
		filePath  = where + "\\POSlogs\\";
		
		menuBarPanel = new MenuBar(this);
		keyPadPanel = new KeyPadPanel(this);
		passwordPanel = new PasswordPanel(this);
		menuPanel = new MenuPanel(this);
		changeMenuPanel = new ChangeMenuPanel(this);
		statisticPanel = new StatisticPanel(this);
		changePasswordPanel = new ChangePasswordPanel(this);
		
		// add initial components
		
		startupPanel.add(passwordPanel);
		startupPanel.add(keyPadPanel);
		add(startupPanel);
		
		// add(menuPanel);
		// test.setText(where);
		// add(test);
		// add(changeMenuPanel);
		// add(statisticPanel);
		// add(changePasswordPanel);
		
		
		setVisible(true);
	}

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
            		"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }
    
	public static void main(String args[]){
		
		
		Startup start = new Startup();
		
		
		// System.out.println(System.getenv("ProgramFiles"));
		
		/*
		char[] printdata = "hello world\n".toCharArray();
		DocFlavor flavor = DocFlavor.CHAR_ARRAY.TEXT_PLAIN;
		PrintService pservice = PrintServiceLookup.lookupDefaultPrintService();
		DocPrintJob pjob = pservice.createPrintJob();
		Doc doc= new SimpleDoc(printdata, flavor, null);
		try {
			pjob.print(doc, null);
			System.out.println("printed");
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			System.out.println("not printed!");
			e.printStackTrace();
			System.out.println("not printed?");
		}
		*/
		
	}
    
    
}
