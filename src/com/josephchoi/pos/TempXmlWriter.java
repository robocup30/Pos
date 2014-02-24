package com.josephchoi.pos;
import java.io.*;
import java.text.*;
import java.util.*;
import nu.xom.*;

public class TempXmlWriter {
	
	PasswordPanel passwordPanel;
	
	public TempXmlWriter(PasswordPanel in){
		
		passwordPanel = in;
		
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportDate = df.format(today);
		
		
		Element root = new Element("root");
		Element properties = new Element("properties");
		Element password = new Element("password");
		Element date = new Element("date");
		Element menu = new Element("menu");
		
		root.appendChild(date);
		root.appendChild(properties);
		root.appendChild(menu);
		date.appendChild(reportDate);
		properties.appendChild(password);
		password.appendChild("123456");
		
		for(int i = 1; i <= 99; i++){
			Element menuItem = new Element("menuItem");
			Element itemName = new Element("name");
			Element itemPrice = new Element("price");
			Element itemNumber = new Element("itemNumber");
			Element itemColor = new Element("color");
			Element red = new Element("red");
			Element green = new Element("green");
			Element blue = new Element("blue");
			menu.appendChild(menuItem);
			menuItem.appendChild(itemNumber);
			menuItem.appendChild(itemName);
			menuItem.appendChild(itemPrice);
			menuItem.appendChild(itemColor);
			itemColor.appendChild(red);
			itemColor.appendChild(green);
			itemColor.appendChild(blue);
			itemNumber.appendChild(String.valueOf(i));
			if(i < 100){
				itemName.appendChild("");
				itemPrice.appendChild("0");
				red.appendChild("255");
				green.appendChild("255");
				blue.appendChild("255");
			}
		}
		
		Document doc = new Document(root);
		
	    try {
			File propFile = new File(passwordPanel.startup.filePath + "properties.xml");
			FileOutputStream propOutStream = new FileOutputStream(propFile);
	    	
	        Serializer serializer = new Serializer(propOutStream, "ISO-8859-1");
	        serializer.setIndent(4);
	        serializer.setMaxLength(150);
	        serializer.write(doc);
	        propOutStream.close();
	      }
	      catch (IOException ex) {
	         System.err.println(ex); 
	      }
	}
	
}
