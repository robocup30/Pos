package com.josephchoi.pos;
import java.io.*;
import nu.xom.*;

public class TempXmlReader {
	public TempXmlReader(){
		
		try {
			File propertiesFile = new File("C:\\Users\\Joseph\\workspace\\Pos\\properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element root = doc.getRootElement();
			Element menu = root.getFirstChildElement("menu");
			Elements menuItems = menu.getChildElements("menuItem");
			
			for(int i = 0; i <= 9; i++){
				Element menuItem = menuItems.get(i);
				Element name = menuItem.getFirstChildElement("name");
				Element price = menuItem.getFirstChildElement("price");
				String nameString = name.getValue();
				String priceString = price.getValue();
				System.out.println(nameString);
				System.out.println(priceString);
			}

		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String args[]){
		TempXmlReader txr = new TempXmlReader();
	}

}
