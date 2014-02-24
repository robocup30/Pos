package com.josephchoi.pos;
import java.io.*;
import java.text.*;
import java.util.*;
import nu.xom.*;

public class TempXmlWriter2 {
	public TempXmlWriter2(){
		
		// Create an instance of SimpleDateFormat used for formatting 
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat dfd = new SimpleDateFormat("MMM/dd/yyyy");

		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();        
		// Using DateFormat format method we can create a string 
		// representation of a date with the defined format.
		String reportTime;
		String reportDay = dfd.format(today);
		String fileName = "Sales log " + reportDay + ".xml";
		fileName = fileName.replaceAll("/", "-");
		
		int salecounter = 1;
		
		Element root = new Element("root");
		Element sales = new Element("sales");
		Element date = new Element("date");
		
		root.appendChild(date);
		root.appendChild(sales);
		date.appendChild(reportDay);
		
		
		Element currentsalenumber = new Element("currentsalenumber");
		sales.appendChild(currentsalenumber);
		for(int i = 1; i <= 20; i++){
			
			Date now = Calendar.getInstance().getTime();
			reportTime = df.format(now);
			
			Element time = new Element("time");
			Element sale = new Element("sale");
			Element items = new Element("items");
			Element cost = new Element("cost");
			Element paymentMethod = new Element("paymentmethod");
			Element saleNumber = new Element("salenumber");
			
			sales.appendChild(sale);
			sale.appendChild(time);
			time.appendChild(reportTime);
			sale.appendChild(saleNumber);
			sale.appendChild(items);
			sale.appendChild(cost);
			sale.appendChild(paymentMethod);
			
			
			saleNumber.appendChild(Integer.toString(i));
			
			if(i == 1){
				items.appendChild("2 California Rolls");
				cost.appendChild("4.99");
			}
			
			salecounter++;
		}
		
		currentsalenumber.appendChild(Integer.toString(salecounter));
		
		Document doc = new Document(root);
		
	    try {
			File propFile = new File(fileName);
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
	
	public static void main(String args[]){
		TempXmlWriter2 txw = new TempXmlWriter2();
	}
}
