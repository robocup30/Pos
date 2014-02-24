package com.josephchoi.pos;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;
import java.io.*;
import nu.xom.*;

// main menu panel, everything will be added here and then to startup
public class MenuPanelBackup extends JPanel implements ActionListener, MouseListener{
	
	Startup startup;
	FlowLayout flo;
	GridLayout grid;
	GridLayout gridOrder;
	GridLayout grid2by2;
	GridLayout grid4by1;
	GridLayout grid3by2;
	GridLayout grid4by4;
	BorderLayout border;
	int itemCounter = 0; // counter for itemLabel on order screen
	String[] itemName;
	int[] itemCount;
	int[] itemPrice;
	
	// menu buttons
	JPanel menu;
	
	// rolls
	JButton menuItemButtons[];
	
	// order screen buttons
	JPanel orderScreen;
	JPanel orderList;
	JLabel screenLabel;
	
	JPanel totalPanel;
	JLabel totalPanelCostLabel;
	JLabel totalPanelCost;
	int totalPanelCostInt;
	double totalPanelCostDouble;
    String totalPanelCostDoubleDecimal;
	JLabel totalPanelPaidLabel;
	JLabel totalPanelPaid;
	JLabel totalPanelChangeLabel;
	JLabel totalPanelChange;

	JLabel screenItems[];
	JPopupMenu popUp;
	JLabel itemPressed;
	
	int menuItemSize;
	
	// payment Option panel
	JPanel paymentOptionPanel;
	JButton cash;
	JButton debit;
	JButton visa;
	
	// set up payment popup
	JPopupMenu paymentPopup;
	
	JPanel costPanel;
	JLabel totalPopupLabel;
	JLabel totalPopup;
	JLabel paidPopupLabel;
	JLabel paidPopup;
	int paidPopupInt;
	double paidPopupDouble;
	String paidPopupString;
	JLabel changePopupLabel;
	JLabel changePopup;
	String changeDoubleDecimal;
	String paidPopupDoubleDecimal;
	
	JPanel keyPad;
	JButton button0;
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	JButton button9;
	JButton okButton;
	JButton clearButton;
	JButton dollar10;
	JButton dollar20;
	JButton dollar50;
	JButton exact;
	
	
	public MenuPanelBackup(Startup in){
		
		startup = in;
		flo = new FlowLayout();
		grid = new GridLayout(8, 8);
		grid2by2 = new GridLayout(2, 2);
		grid4by1 = new GridLayout(4, 1);
		grid3by2 = new GridLayout(3, 2);
		grid4by4 = new GridLayout(4, 4);
		gridOrder = new GridLayout(17, 1);
		border = new BorderLayout();
		setLayout(flo);
		
		// declare panels
		menu = new JPanel();
		orderScreen = new JPanel();
		orderScreen.setLayout(border);
		orderScreen.setPreferredSize(new Dimension(300, 700));
		orderScreen.setBackground(Color.LIGHT_GRAY);
		orderList = new JPanel();
		orderList.setLayout(gridOrder);
		orderList.setBackground(Color.WHITE);
		totalPanel = new JPanel();
		paymentOptionPanel = new JPanel();
		paymentOptionPanel.setLayout(grid4by1);
		keyPad = new JPanel();
		costPanel = new JPanel();
		
		// keypad buttons
		button0 = new JButton("0");
		button0.setPreferredSize(new Dimension(100, 100));
		button1 = new JButton("1");
		button2 = new JButton("2");
		button3 = new JButton("3");
		button4 = new JButton("4");
		button5 = new JButton("5");
		button6 = new JButton("6");
		button7 = new JButton("7");
		button8 = new JButton("8");
		button9 = new JButton("9");
		okButton = new JButton("Ok");
		clearButton = new JButton("Clear");
		dollar10 = new JButton("$10");
		dollar20 = new JButton("$20");
		dollar50 = new JButton("$50");
		exact = new JButton("Exact amount");
		
		button0.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		okButton.addActionListener(this);
		clearButton.addActionListener(this);
		dollar10.addActionListener(this);
		dollar20.addActionListener(this);
		dollar50.addActionListener(this);
		exact.addActionListener(this);
		
		
		keyPad.add(button1);
		keyPad.add(button2);
		keyPad.add(button3);
		keyPad.add(dollar10);
		keyPad.add(button4);
		keyPad.add(button5);
		keyPad.add(button6);
		keyPad.add(dollar20);
		keyPad.add(button7);
		keyPad.add(button8);
		keyPad.add(button9);
		keyPad.add(dollar50);
		keyPad.add(okButton);
		keyPad.add(button0);
		keyPad.add(clearButton);
		keyPad.add(exact);
		
		
		menuItemButtons = new JButton[60]; /////// make 60 into how many items there are
		itemCount = new int[60];
		itemPrice = new int[60];
		itemName = new String[60];
		
		
		// call XML
		// creates JButtons for items
		try {
			File propertiesFile = new File("C:\\Users\\Joseph\\workspace\\Pos\\properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element root = doc.getRootElement();
			Element menuElement = root.getFirstChildElement("menu");
			Elements menuItems = menuElement.getChildElements("menuItem");
			menuItemSize = menuItems.size();
			
			for(int i = 0; i < menuItemSize; i++){
				Element menuItem = menuItems.get(i);
				Element name = menuItem.getFirstChildElement("name");
				Element price = menuItem.getFirstChildElement("price");
				String nameString = name.getValue();
				String priceString = price.getValue();
				if(nameString != ""){
					menuItemButtons[i] = new JButton(nameString);
					menuItemButtons[i].setPreferredSize(new Dimension(120, 100));
					menuItemButtons[i].addActionListener(this);
					double priceDouble = 100 * Double.parseDouble(priceString);
					int priceInt = (int) priceDouble;
					itemName[i] = nameString;
					itemCount[i] = 0;
					itemPrice[i] = priceInt;
				}else{
					menuItemButtons[i] = new JButton("empty");
					menuItemButtons[i].setPreferredSize(new Dimension(120, 100));
					itemName[i] = "empty";
					itemCount[i] = 0;
					itemPrice[i] = 0;
				}
				menu.add(menuItemButtons[i]);
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
		
		// menu items
		
		menu.setLayout(grid);

		// order screen items
		screenLabel = new JLabel("Orders");
		screenItems = new JLabel[999];
		totalPanelCostLabel = new JLabel("Total: ");
		totalPanelCost = new JLabel(".00");
		totalPanelPaidLabel = new JLabel("Paid: ");
		totalPanelPaid = new JLabel(".00");
		totalPanelChangeLabel = new JLabel("Change: ");
		totalPanelChange = new JLabel(".00");
		
		for(int i = 0; i < 998; i++){
			screenItems[i] = new JLabel("");
		}
		
		// set up popup of order screen
		popUp = new JPopupMenu();
		JButton remove = new JButton("Remove");
		remove.addActionListener(this);
		popUp.add(remove);
		JButton count = new JButton("Item number");
		count.addActionListener(this);
		popUp.add(count);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		popUp.add(cancel);
		JButton discount = new JButton("Discount");
		popUp.add(discount);
		discount.addActionListener(this);
		popUp.setLayout(grid2by2);
		
		// set up payment popup
		paymentPopup = new JPopupMenu();
		totalPopupLabel = new JLabel("Total: ");
		totalPopup = new JLabel (".00");
		paidPopupLabel = new JLabel("Paid: ");
		paidPopup = new JLabel(".00");
		changePopupLabel = new JLabel("Chage: ");
		changePopup = new JLabel(".00");
		costPanel.setLayout(grid2by2);
		keyPad.setLayout(grid4by4);
		paidPopupInt = 0;
		paidPopupDouble = 0;
		paidPopupString = "";
		totalPanelCostDoubleDecimal = ".00";
		
		costPanel.add(totalPopupLabel);
		costPanel.add(totalPopup);
		costPanel.add(paidPopupLabel);
		costPanel.add(paidPopup);
		paymentPopup.add(costPanel);
		paymentPopup.add(keyPad);
		
		totalPanel.add(totalPanelCostLabel);
		totalPanel.add(totalPanelCost);
		totalPanel.add(totalPanelPaidLabel);
		totalPanel.add(totalPanelPaid);
		totalPanel.add(totalPanelChangeLabel);
		totalPanel.add(totalPanelChange);
		
		orderScreen.add(screenLabel, BorderLayout.NORTH);
		orderScreen.add(orderList, BorderLayout.CENTER);
		orderScreen.add(totalPanel, BorderLayout.SOUTH);
		
		// paymentOption Items
		cash = new JButton("Cash");
		cash.addActionListener(this);
		debit = new JButton("Debit");
		debit.addActionListener(this);
		visa = new JButton("Visa");
		visa.addActionListener(this);
		
		paymentOptionPanel.add(cash);
		cash.setPreferredSize(new Dimension(100, 80));
		paymentOptionPanel.add(debit);
		paymentOptionPanel.add(visa);
		
		// add JPanels to Menu Panel
		add(orderScreen);
		add(menu);
		add(paymentOptionPanel);
	}

	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();

		if(source == "Remove"){ // remove selected item on order screen
			Class sourcee = e.getClass();
			orderList.remove(itemPressed);
			popUp.setVisible(false);
			invalidate();
			validate();
			repaint();
			
			
			String labelString = itemPressed.getText();
			totalPanelCostInt = 0;
			for(int i = 0; i < menuItemSize; i++){
				if(labelString == "1 " + itemName[i]){
					itemCount[i] =- 1;
					System.out.println("fd");
				}
				totalPanelCostInt += itemCount[i] * itemPrice[i];
				totalPanelCostDouble = (double) totalPanelCostInt / 100;
			    DecimalFormat df = new DecimalFormat("#.00");
			    totalPanelCostDoubleDecimal = df.format(totalPanelCostDouble);
			}
			
			
		}else if(source == "Cancel"){
			popUp.setVisible(false);
		}else if(source == "Item number"){
			
		}else if(source == "Discount"){
			
		}else if(source == "Cash"){
			paymentPopup.setLocation(300, 300);
			paymentPopup.setInvoker(paymentPopup);
			paymentPopup.setVisible(true);
			invalidate();
			validate();
			repaint();
		}else if(source == "Visa"){
			paymentPopup.setLocation(300, 300);
			paymentPopup.setInvoker(paymentPopup);
			paymentPopup.setVisible(true);
			invalidate();
			validate();
			repaint();
		}else if(source == "Debit"){
			paymentPopup.setLocation(300, 300);
			paymentPopup.setInvoker(paymentPopup);
			paymentPopup.setVisible(true);
			invalidate();
			validate();
			repaint();
		}else if(source == "0" || source == "1" || source == "2" || source == "3" || source == "4"
				|| source == "5" || source == "6" || source == "7" || source == "8" || source == "9"){
			if(paidPopupString.length() < 9){
				paidPopupString = paidPopupString + source;
				paidPopupDouble = (double) Integer.parseInt(paidPopupString) / 100;
			    DecimalFormat df = new DecimalFormat("#.00");
			    paidPopupDoubleDecimal = df.format(paidPopupDouble);
				paidPopup.setText(paidPopupDoubleDecimal);
			}
		}else if (source == "Clear"){
			paidPopup.setText(".00");
			paidPopupString = "";
		}else if (source == "Ok"){
			double change = paidPopupDouble - totalPanelCostDouble;
		    DecimalFormat df = new DecimalFormat("#.00");
		    changeDoubleDecimal = df.format(change);
			totalPanelChange.setText(changeDoubleDecimal);
			totalPanelPaid.setText(paidPopupDoubleDecimal);
			if(paidPopupDouble > totalPanelCostDouble){
				paymentPopup.setVisible(false);
				for(int i = 0; i < 998; i++){
					orderList.remove(screenItems[i]);
					invalidate();
					validate();
					repaint();
				}
				for(int i = 0; i < menuItemSize; i++){
					itemCount[i]  = 0;
				}
				paidPopupString = "";
				paidPopup.setText(".00");
				itemCounter = 0;
			}
		}else if (source == "Exact amount"){
			paidPopup.setText(totalPanelCostDoubleDecimal);
			paidPopupString = "";
		}else if (source == "$10"){
			paidPopupDouble = 10.00;
			paidPopupDoubleDecimal = "10.00";
			paidPopup.setText("10.00");
			totalPanelPaid.setText("10.00");
			paidPopupString = "";
		}else if (source == "$20"){
			paidPopupDouble = 20.00;
			paidPopupDoubleDecimal = "20.00";
			paidPopup.setText("20.00");
			totalPanelPaid.setText("20.00");
			paidPopupString = "";
		}else if (source == "$50"){
			paidPopupDouble = 50.00;
			paidPopupDoubleDecimal = "50.00";
			paidPopup.setText("50.00");
			totalPanelPaid.setText("50.00");
			paidPopupString = "";
		}else{ // when item is pressed, create item at the order screen
			screenItems[itemCounter] = new JLabel("");
			screenItems[itemCounter].addMouseListener(this);
			screenItems[itemCounter].setText("1 " + source);
			orderList.add(screenItems[itemCounter]);
			totalPanelCostInt = 0; // resets the cost
			for(int i = 0; i < menuItemSize; i++){
				if(source == itemName[i]){
					itemCount[i] += 1;
				}
				totalPanelCostInt += itemCount[i] * itemPrice[i];
				totalPanelCostDouble = (double) totalPanelCostInt / 100;
			    DecimalFormat df = new DecimalFormat("#.00");
			    totalPanelCostDoubleDecimal = df.format(totalPanelCostDouble);
			}
			totalPanelCost.setText(totalPanelCostDoubleDecimal);
			totalPopup.setText(totalPanelCostDoubleDecimal);
			itemCounter++;
			invalidate();
			validate();
			repaint();
		}
		// add total cost
		
	}

	// when order list item is clicked, create popup with options
	public void mouseClicked(MouseEvent e) {
		Class source = e.getClass();
		itemPressed = (JLabel)e.getSource();
		for(int i = 0; i < 100; i++){
			if(e.getSource() == screenItems[i]){
				System.out.println(i);
			}
		}
		PointerInfo a = MouseInfo.getPointerInfo();
		Point b  = a.getLocation();
		int x = (int)b.getX();
		int y = (int)b.getY();
		popUp.setLocation(x, y);
        popUp.setInvoker(popUp);
        popUp.setVisible(true);
		invalidate();
		validate();
		repaint();
		
	}

	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}