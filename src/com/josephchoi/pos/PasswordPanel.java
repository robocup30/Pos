package com.josephchoi.pos;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import nu.xom.*;

public class PasswordPanel extends JPanel implements KeyListener{
	
	Startup startup;
	
	JLabel passLabel;
	JLabel passwordStatusLabel;
	JPasswordField pass;
	String defaultPassword = "123456";
	String userSetPassword;
	FlowLayout flo;

	
	public PasswordPanel(Startup in) {
		
		startup = in;
		
		flo = new FlowLayout();
		setLayout(flo);
		
		try {
			File propertiesFile = new File(startup.filePath + "properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element root = doc.getRootElement();
			Element properties = root.getFirstChildElement("properties");
			Element password = properties.getFirstChildElement("password");
			userSetPassword = password.getValue();
			
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			TempXmlWriter txw = new TempXmlWriter(this);
			try {
				File propertiesFile = new File(startup.filePath + "properties.xml");
				Builder builder = new Builder();
				Document doc = builder.build(propertiesFile);
				
				Element root = doc.getRootElement();
				Element properties = root.getFirstChildElement("properties");
				Element password = properties.getFirstChildElement("password");
				userSetPassword = password.getValue();
				
			} catch (ValidityException ex) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParsingException ex) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException ex) {
			}
			
		}

		passwordStatusLabel = new JLabel("");
		passLabel = new JLabel("Password (Default: 123456)");
		pass = new JPasswordField(10);
		passwordStatusLabel.setPreferredSize(new Dimension(150, 10));

		// add listener
		pass.addKeyListener(this);
		
		// add initial components
		add(passLabel);
		add(pass);
		add(passwordStatusLabel);
		
		setVisible(true);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			startup.keyPadPanel.okButton.doClick();
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}
    
	public void setPassword(){
		try {
			File propertiesFile = new File(startup.filePath + "properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element root = doc.getRootElement();
			Element properties = root.getFirstChildElement("properties");
			Element password = properties.getFirstChildElement("password");
			userSetPassword = password.getValue();
			
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			
		}
	}
    
}
