package com.josephchoi.pos;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JTextField;


public class Print {
	
	MenuPanelOrderPanel orderPanel;
	DecimalFormat dfp;
	
	public Print(MenuPanelOrderPanel in){
		
		orderPanel = in;
		
		dfp = new DecimalFormat("#.00");
	}
	
	
	public void printOrder(){
		
		String orderString = "";
		
		for(int i = 0; i < orderPanel.orderLabel.length; i++){
			if(orderPanel.itemCountOfLabel[i] != 0){
				orderString = orderString + orderPanel.itemCountOfLabel[i] + " " + orderPanel.itemNameOfLabel[i] + "\n";
			}
		}
		
		System.out.println(orderString);
	}
	
	public void printRecipt(){
		
		Date now = Calendar.getInstance().getTime();
		DateFormat dfd = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
		String reciptString = "";
		reciptString += "Pine Sushi Square\n";
		reciptString += dfd.format(now) + "\n";
		
		// System.out.println("<html><div style=\"text-align: center;\">" + "Pine Sushi Square" + "<html />");
		for(int i = 0; i < orderPanel.orderLabel.length; i++){
			if(orderPanel.itemCountOfLabel[i] != 0){
				reciptString = reciptString + orderPanel.itemCountOfLabel[i] + " " + orderPanel.itemNameOfLabel[i] + " $" + dfp.format(orderPanel.itemPriceOfLabel[i] * orderPanel.itemPriceModifier[i]) + "\n";
			}
		}
		
		
		
		
		
		reciptString += "Subtotal: $" + orderPanel.menuPanel.costString + "\n";
		reciptString += "GST: $" + orderPanel.menuPanel.costTaxOnlyString + "\n";
		reciptString += "Total: $" + orderPanel.menuPanel.costAfterTaxString + "\n";
		
		
		System.out.println(reciptString);
	}
	
	public void openRegister(){
		
		/*
		JTextField text = new JTextField(".");
		try {
		    boolean complete = text.print(null,
                    null, 
                    false,
                    PrintServiceLookup.lookupDefaultPrintService(),
                    null,
                    false);
		} catch (PrinterException pe) {
		}
	*/
	}
	
	public void printBoth(){
		openRegister();
		
		
		printOrder();
		
		try {
		    Thread.sleep(500);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		printRecipt();
	}
	
}
