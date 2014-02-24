package com.josephchoi.pos;
import java.io.*;
import java.text.*;
import java.util.*;
import nu.xom.*;

public class SalesLogWriter {
	
	MenuPanelPaymentOptionPanel paymentOption;
	String saleNumberString;
	int saleNumberInt;
	
	Document doc;
	
	public SalesLogWriter(MenuPanelPaymentOptionPanel in){
		
		paymentOption = in;
		
		saleNumberInt = 1;
		saleNumberString = "1";
		
	}
	
	public void writeLog(){
		
		
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		DateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
		// DateFormat dfd = new SimpleDateFormat("MMM/dd/yyyy");

		Date today = Calendar.getInstance().getTime();        

		String reportTime;
		String reportDay = dfd.format(today);
		String fileName = paymentOption.menuPanel.startup.filePath + "sales logs\\Sales log " + reportDay + ".xml";
		fileName = fileName.replaceAll("/", "-");
		
		try {
			File salesFile = new File(fileName);
			Builder builder = new Builder();
			doc = builder.build(salesFile);
			
			Element root = doc.getRootElement();
			Element date = root.getFirstChildElement("date");
			Element sales = root.getFirstChildElement("sales");
			Element currentsalenumber = sales.getFirstChildElement("currentsalenumber");
			saleNumberString = currentsalenumber.getValue();
			saleNumberInt = Integer.parseInt(saleNumberString);
			
			
			Element sale = new Element("sale");
			sales.appendChild(sale);
			Element time = new Element("time");
			Element saleNumber = new Element("salenumber");
			Element items = new Element("items");
			Element cost = new Element("cost");
			Element paymentMethod = new Element("paymentmethod");
			sale.appendChild(time);
			sale.appendChild(saleNumber);
			sale.appendChild(items);
			sale.appendChild(cost);
			sale.appendChild(paymentMethod);
			
			Date now = Calendar.getInstance().getTime();
			reportTime = df.format(now);
			time.appendChild(reportTime);
			saleNumber.appendChild(saleNumberString);
			
			for(int i = 0; i < paymentOption.menuPanel.orderPanel.counter; i++){
				Element item = new Element("item");
				items.appendChild(item);
				Element name = new Element("name");
				item.appendChild(name);
				Element count = new Element("count");
				item.appendChild(count);
				Element price = new Element("price");
				item.appendChild(price);
				name.appendChild(paymentOption.menuPanel.orderPanel.itemNameOfLabel[i]);
				count.appendChild("" + paymentOption.menuPanel.orderPanel.itemCountOfLabel[i]);
				price.appendChild("" + paymentOption.menuPanel.orderPanel.getCost(i));
			}
			cost.appendChild(paymentOption.menuPanel.costAfterTaxString);
			paymentMethod.appendChild(paymentOption.paymentOptionPressed);

			saleNumberInt++;
			saleNumberString = String.valueOf(saleNumberInt);
			currentsalenumber.removeChildren();
			currentsalenumber.appendChild(saleNumberString);
			
			FileOutputStream propOutStream = new FileOutputStream(salesFile);
	        Serializer serializer = new Serializer(propOutStream, "ISO-8859-1");
	        serializer.setIndent(4);
	        serializer.setMaxLength(150);
	        serializer.write(doc);
	        propOutStream.close();
			
			
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			Element root = new Element("root");
			Element sales = new Element("sales");
			Element date = new Element("date");
			
			root.appendChild(date);
			root.appendChild(sales);
			date.appendChild(reportDay);
			
			Element currentsalenumber = new Element("currentsalenumber");
			sales.appendChild(currentsalenumber);

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
			sale.appendChild(saleNumber);
			sale.appendChild(items);
			sale.appendChild(cost);
			sale.appendChild(paymentMethod);
			
			time.appendChild(reportTime);
			saleNumber.appendChild(saleNumberString);
			
			for(int i = 0; i < paymentOption.menuPanel.orderPanel.counter; i++){
				Element item = new Element("item");
				items.appendChild(item);
				Element name = new Element("name");
				item.appendChild(name);
				Element count = new Element("count");
				item.appendChild(count);
				Element price = new Element("price");
				item.appendChild(price);
				name.appendChild(paymentOption.menuPanel.orderPanel.itemNameOfLabel[i]);
				count.appendChild("" + paymentOption.menuPanel.orderPanel.itemCountOfLabel[i]);
				price.appendChild("" + paymentOption.menuPanel.orderPanel.getCost(i));
			}
			cost.appendChild(paymentOption.menuPanel.costAfterTaxString);
			paymentMethod.appendChild(paymentOption.paymentOptionPressed);

			saleNumberInt++;
			saleNumberString = String.valueOf(saleNumberInt);
			
			currentsalenumber.appendChild(saleNumberString);
			
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
		

	}
}
