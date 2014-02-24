package com.josephchoi.pos;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

import java.io.*;
import nu.xom.*;

// main menu panel, everything will be added here and then to startup
public class MenuPanel extends JPanel implements ActionListener, MouseListener{
	
	Startup startup;

	// Panels of MenuPanel
	MenuPanelOrderPanel orderPanel;
	MenuPanelItemButtonPanel itemButtonPanel;
	MenuPanelPaymentOptionPanel paymentOptionPanel;
	
	// variables for MenuPanel
	double cost;
	double paid;
	String paidString;
	double change;
	String changeString;
	String costString;
	double costAfterTax;
	String costAfterTaxString;
	double costTaxOnly;
	String costTaxOnlyString;
	
	
	DecimalFormat df;
	
	public MenuPanel(Startup in){
		
		startup = in;
		
		orderPanel = new MenuPanelOrderPanel(this);
		itemButtonPanel = new MenuPanelItemButtonPanel(this);
		paymentOptionPanel = new MenuPanelPaymentOptionPanel(this);
		
		df = new DecimalFormat("#.00");
		
		cost = 0;
		paid = 0;
		change = 0;
		costAfterTax = 0;
		costTaxOnly = 0;
		
		
		add(orderPanel);
		add(itemButtonPanel);
		add(paymentOptionPanel);
		

	}
	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}