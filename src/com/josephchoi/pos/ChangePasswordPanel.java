package com.josephchoi.pos;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.*;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;


public class ChangePasswordPanel extends JPanel implements ActionListener{
	
	Startup startup;
	
	JLabel currentLabel;
	JLabel newLabel;
	JLabel reLabel;
	JPasswordField currentField;
	JPasswordField newField;
	JPasswordField reField;
	JButton saveButton;
	JLabel statusLabel;
	
	char[] currentChar;
	String currentString;
	char[] newChar;
	String newString;
	char[] reChar;
	String reString;
	
	JPanel panel = new JPanel();
	
	ChangePasswordPanel(Startup in){
		
		startup = in;
		
		currentLabel = new JLabel("Current Password: ");
		newLabel = new JLabel("New Password: ");
		reLabel = new JLabel("Retype Password: ");
		
		currentField = new JPasswordField(12);
		newField = new JPasswordField(12);
		reField = new JPasswordField(12);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		
		statusLabel = new JLabel();
		statusLabel.setText("");
		
		panel.setLayout(new GridLayout(4, 2));
		
		panel.add(currentLabel);
		panel.add(currentField);
		panel.add(newLabel);
		panel.add(newField);
		panel.add(reLabel);
		panel.add(reField);
		panel.add(saveButton);
		panel.add(statusLabel);
		add(panel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		currentChar = currentField.getPassword();
		currentString = new String(currentChar);
		newChar = newField.getPassword();
		newString = new String(newChar);
		reChar = reField.getPassword();
		reString = new String(reChar);
		
		if(reString.equals(newString) && currentString.equals(startup.passwordPanel.userSetPassword)){
			try {
				File propertiesFile = new File(startup.filePath + "properties.xml");
				Builder builder = new Builder();
				Document doc = builder.build(propertiesFile);
				
				Element root = doc.getRootElement();
				Element properties = root.getFirstChildElement("properties");
				Element password = properties.getFirstChildElement("password");
				password.removeChildren();
				password.appendChild(newString);
				
				FileOutputStream propOutStream = new FileOutputStream(propertiesFile);
		        Serializer serializer = new Serializer(propOutStream, "ISO-8859-1");
		        serializer.setIndent(4);
		        serializer.setMaxLength(150);
		        serializer.write(doc);
		        propOutStream.close();
		        
		        statusLabel.setText("Saved");
				
			} catch (ValidityException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (ParsingException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (reString.equals(newString)){
			statusLabel.setText("Wrong current password");
		} else {
			statusLabel.setText("Password does not match");
		}
		
	}
	
	public void initalize(){
		currentField.setText("");
		newField.setText("");
		reField.setText("");
		statusLabel.setText("");
	}
}
