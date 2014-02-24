package com.josephchoi.pos;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import javax.swing.*;


public class MenuPanelOrderPanel extends JPanel implements MouseListener, ActionListener{
	
	
	MenuPanel menuPanel;
	
	Print printClass;
	
	JPanel topPanel;
	JPanel midPanel;
	JPanel bottomPanel;
	
	// topPanel components
	JLabel topPanelLabel;
	
	// midPanel components
	JLabel[] orderLabel;
	int[] itemCountOfLabel;
	double[] itemPriceOfLabel;
	String[] itemNameOfLabel;   // this is itemName
	double[] itemPriceModifier;
	double[] itemOriginalPrice;
	double taxModifier;
	int counter;
	GridLayout midLayout;
	int indexOfLabelPressed;
	JPopupMenu popup;
	JPopupMenu discountPopup;
	JPopupMenu countPopup;
	double finalModifier;
	double finalPercentModifier;
	
	// bottomPanel components
	JLabel costLabel;
	JLabel paidLabel;
	JLabel changeLabel;
	JLabel costAfterTaxLabel;
	JLabel costTaxOnlyLabel;
	GridLayout bottomLayout;
	
	// popup components
	JButton removeButton;
	JButton cancelButton;
	JButton discountButton;
	JButton countButton;
	GridLayout popupLayout;
	
	// discountPopup components
	BorderLayout discountLayout;
	GridLayout percentLayout;
	
	JLabel itemDiscountPopupLabel;
	String itemDiscountPopupString;
	
	GridLayout discountKeypadLayout;
	
	JPanel discountKeypadPanel;
	JPanel percentagePanel;
	JPanel resetPanel;
	JButton resetDiscountButton;
	JButton percentDiscountButton;
	JButton discountButton0;
	JButton discountButton1;
	JButton discountButton2;
	JButton discountButton3;
	JButton discountButton4;
	JButton discountButton5;
	JButton discountButton6;
	JButton discountButton7;
	JButton discountButton8;
	JButton discountButton9;
	JButton okDiscountButton;
	JButton clearDiscountButton;
	
	// countPopup components
	
	BorderLayout countLayout;
	
	JLabel itemCountPopupLabel;
	String itemCountPopupString;
	
	GridLayout keypadLayout;
	
	JPanel countKeypadPanel;
	JButton countButton0;
	JButton countButton1;
	JButton countButton2;
	JButton countButton3;
	JButton countButton4;
	JButton countButton5;
	JButton countButton6;
	JButton countButton7;
	JButton countButton8;
	JButton countButton9;
	JButton okCountButton;
	JButton clearCountButton;
	
	
	BorderLayout border;
	
	public MenuPanelOrderPanel(MenuPanel in){
		
		menuPanel = in;
		border = new BorderLayout();
		setLayout(border);
		setPreferredSize(new Dimension(280, 800));
		
		taxModifier = 1.05;
		finalModifier = 0;
		finalPercentModifier = 1;
		
		topPanel = new JPanel();
		midPanel = new JPanel();
		bottomPanel = new JPanel();
		
		// set up topPanel components
		topPanelLabel = new JLabel("Reset all");
		topPanelLabel.addMouseListener(this);
		topPanel.setBackground(Color.LIGHT_GRAY);
		// set up midPanel components
		midLayout = new GridLayout(16, 1);
		midPanel.setLayout(midLayout);
		orderLabel = new JLabel[300];
		itemCountOfLabel = new int[300];
		itemPriceOfLabel = new double[300];
		itemNameOfLabel = new String[300];
		itemPriceModifier = new double[300];
		itemOriginalPrice = new double[300];
		counter = 0;
		popup = new JPopupMenu();
		discountPopup = new JPopupMenu();
		countPopup = new JPopupMenu();
		midPanel.setBackground(Color.WHITE);
		// set up bottomPanel components
		bottomLayout = new GridLayout(3, 2);
		bottomPanel.setLayout(bottomLayout);
		costLabel = new JLabel("Cost: .00");
		costLabel.addMouseListener(this);
		paidLabel = new JLabel("Paid: .00");
		changeLabel = new JLabel("Change: .00");
		costTaxOnlyLabel = new JLabel("Tax: .00");
		costAfterTaxLabel = new JLabel("Total: .00");
		// set up popup components
		popupLayout = new GridLayout(2, 2);
		popup.setLayout(popupLayout);
		removeButton = new JButton("Remove");
		removeButton.addActionListener(this);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		discountButton = new JButton("Discount");
		discountButton.addActionListener(this);
		countButton = new JButton("Count");
		countButton.addActionListener(this);
		// set up discountPopup components
		itemDiscountPopupLabel = new JLabel("Discount: ");
		itemDiscountPopupString = "";
		discountKeypadPanel = new JPanel();
		percentagePanel = new JPanel();
		resetPanel = new JPanel();

		
		discountLayout = new BorderLayout();
		discountPopup.setLayout(discountLayout);
		discountKeypadLayout = new GridLayout(4, 3);
		discountKeypadPanel.setLayout(discountKeypadLayout);
		percentLayout = new GridLayout(2, 1);
		percentagePanel.setLayout(percentLayout);
		resetPanel.setLayout(percentLayout);
		
		discountButton0 = new JButton("0");
		discountButton0.setPreferredSize(new Dimension(90, 90));
		discountButton1 = new JButton("1");
		discountButton2 = new JButton("2");
		discountButton3 = new JButton("3");
		discountButton4 = new JButton("4");
		discountButton5 = new JButton("5");
		discountButton6 = new JButton("6");
		discountButton7 = new JButton("7");
		discountButton8 = new JButton("8");
		discountButton9 = new JButton("9");
		okDiscountButton = new JButton("$");
		clearDiscountButton = new JButton("Cancel");
		percentDiscountButton = new JButton("%");
		resetDiscountButton = new JButton("Reset");
		discountButton0.addActionListener(this);
		discountButton1.addActionListener(this);
		discountButton2.addActionListener(this);
		discountButton3.addActionListener(this);
		discountButton4.addActionListener(this);
		discountButton5.addActionListener(this);
		discountButton6.addActionListener(this);
		discountButton7.addActionListener(this);
		discountButton8.addActionListener(this);
		discountButton9.addActionListener(this);
		okDiscountButton.addActionListener(this);
		clearDiscountButton.addActionListener(this);
		percentDiscountButton.addActionListener(this);
		resetDiscountButton.addActionListener(this);
		percentagePanel.add(okDiscountButton);
		percentagePanel.add(percentDiscountButton);
		resetPanel.add(resetDiscountButton);
		resetPanel.add(clearDiscountButton);
		
		discountKeypadPanel.add(discountButton1);
		discountKeypadPanel.add(discountButton2);
		discountKeypadPanel.add(discountButton3);
		discountKeypadPanel.add(discountButton4);
		discountKeypadPanel.add(discountButton5);
		discountKeypadPanel.add(discountButton6);
		discountKeypadPanel.add(discountButton7);
		discountKeypadPanel.add(discountButton8);
		discountKeypadPanel.add(discountButton9);
		discountKeypadPanel.add(percentagePanel);
		discountKeypadPanel.add(discountButton0);
		discountKeypadPanel.add(resetPanel);
		// set up countPopup components
		itemCountPopupLabel = new JLabel("Item count: ");
		itemCountPopupString = "";
		countKeypadPanel = new JPanel();
		
		countLayout = new BorderLayout();
		countPopup.setLayout(countLayout);
		keypadLayout = new GridLayout(4, 3);
		countKeypadPanel.setLayout(keypadLayout);
		
		countButton0 = new JButton("0");
		countButton0.setPreferredSize(new Dimension(90, 90));
		countButton1 = new JButton("1");
		countButton2 = new JButton("2");
		countButton3 = new JButton("3");
		countButton4 = new JButton("4");
		countButton5 = new JButton("5");
		countButton6 = new JButton("6");
		countButton7 = new JButton("7");
		countButton8 = new JButton("8");
		countButton9 = new JButton("9");
		okCountButton = new JButton("Ok");
		clearCountButton = new JButton("Cancel");
		countButton0.addActionListener(this);
		countButton1.addActionListener(this);
		countButton2.addActionListener(this);
		countButton3.addActionListener(this);
		countButton4.addActionListener(this);
		countButton5.addActionListener(this);
		countButton6.addActionListener(this);
		countButton7.addActionListener(this);
		countButton8.addActionListener(this);
		countButton9.addActionListener(this);
		okCountButton.addActionListener(this);
		clearCountButton.addActionListener(this);
		
		countKeypadPanel.add(countButton1);
		countKeypadPanel.add(countButton2);
		countKeypadPanel.add(countButton3);
		countKeypadPanel.add(countButton4);
		countKeypadPanel.add(countButton5);
		countKeypadPanel.add(countButton6);
		countKeypadPanel.add(countButton7);
		countKeypadPanel.add(countButton8);
		countKeypadPanel.add(countButton9);
		countKeypadPanel.add(okCountButton);
		countKeypadPanel.add(countButton0);
		countKeypadPanel.add(clearCountButton);
		
		// add topPanel components
		topPanel.add(topPanelLabel);
		// add midPanel components
		// add bottomPanel components
		bottomPanel.add(costLabel);
		bottomPanel.add(paidLabel);
		bottomPanel.add(costTaxOnlyLabel);
		bottomPanel.add(changeLabel);
		bottomPanel.add(costAfterTaxLabel);
		// add popup components
		popup.add(removeButton);
		removeButton.setPreferredSize(new Dimension(80, 80));
		popup.add(discountButton);
		popup.add(countButton);
		popup.add(cancelButton);
		// add discoundPopup components
		discountPopup.add(itemDiscountPopupLabel, BorderLayout.NORTH);
		discountPopup.add(discountKeypadPanel, BorderLayout.CENTER);
		// add countPopup components
		countPopup.add(itemCountPopupLabel, BorderLayout.NORTH);
		countPopup.add(countKeypadPanel, BorderLayout.CENTER);
		
		printClass = new Print(this);
		
		add(topPanel, BorderLayout.NORTH);
		add(midPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void setCost(){
		double costBeforeDiscount = 0;
		for(int i = 0; i < counter; i++){
			costBeforeDiscount += itemCountOfLabel[i] * itemPriceOfLabel[i] * itemPriceModifier[i];
		}
		menuPanel.cost = costBeforeDiscount - finalModifier;
		menuPanel.cost = menuPanel.cost * finalPercentModifier;  // TODO taxModifier was here
	    menuPanel.costString = menuPanel.df.format(menuPanel.cost);
	    menuPanel.costAfterTax = menuPanel.cost * taxModifier;
	    menuPanel.costAfterTaxString = menuPanel.df.format(menuPanel.costAfterTax);
	    menuPanel.costTaxOnly = menuPanel.costAfterTax - menuPanel.cost;
	    menuPanel.costTaxOnlyString = menuPanel.df.format(menuPanel.costTaxOnly);
		costLabel.setText("Cost: " + menuPanel.costString);
		costAfterTaxLabel.setText("Total: " + menuPanel.costAfterTaxString);
		costTaxOnlyLabel.setText("Tax: " + menuPanel.costTaxOnlyString);
	}
	
	public String getCost(int i){
		double itemCost = 0;
		String itemCostString = "";
		itemCost = itemCountOfLabel[i] * itemPriceOfLabel[i] * itemPriceModifier[i];
	    itemCostString = menuPanel.df.format(itemCost);
		return itemCostString;
	}
	
	public void setLabelText(){
		orderLabel[indexOfLabelPressed].setText("   " + itemCountOfLabel[indexOfLabelPressed] + " " + itemNameOfLabel[indexOfLabelPressed] + " $ " + getCost(indexOfLabelPressed));
	}
	
	public void resetAll(){
		// TODO
		taxModifier = 1.06;
		finalModifier = 0;
		finalPercentModifier = 1;
		for(int i = 0; i<counter; i++){
			midPanel.remove(orderLabel[i]);
			itemCountOfLabel[i] = 0;
		}
		revalidate();
		repaint();
		
		setCost();
		counter = 0;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		indexOfLabelPressed = -1;
		
		for(int i = 0; i < orderLabel.length; i++){
			if(e.getSource() == orderLabel[i]){
				indexOfLabelPressed = i;
				PointerInfo a = MouseInfo.getPointerInfo();
				Point b  = a.getLocation();
				int x = (int)b.getX();
				int y = (int)b.getY();
				popup.setLocation(x, y);
		        popup.setInvoker(popup);
		        popup.setVisible(true);
		        revalidate();
				repaint();
			}
		}
		
		if(e.getSource() == topPanelLabel){
			indexOfLabelPressed = -2;
		}
		
		if(indexOfLabelPressed == -1){
			okDiscountButton.setText("Deduct");
			discountPopup.setLocation(700, 220);
			discountPopup.setInvoker(discountPopup);
			discountPopup.setVisible(true);
			itemDiscountPopupLabel.setText("$" + finalModifier + " off with " + menuPanel.df.format((1 - finalPercentModifier) * 100) + "%" + " discount");
		}else if(indexOfLabelPressed == -2){
			// TODO
			resetAll();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		Object obj = e.getSource();
		
		// popup buttons
		if(obj == removeButton){
			midPanel.remove(orderLabel[indexOfLabelPressed]);
			popup.setVisible(false);
			itemCountOfLabel[indexOfLabelPressed] = 0;
			revalidate();
			repaint();
			
			setCost();
			
		}else if(obj == cancelButton){
			popup.setVisible(false);
		}else if(obj == discountButton){
			discountPopup.setLocation(700, 220);
			discountPopup.setInvoker(discountPopup);
			discountPopup.setVisible(true);
			itemDiscountPopupLabel.setText("Price: " + itemPriceOfLabel[indexOfLabelPressed] + " Discount: " + menuPanel.df.format(((1 - itemPriceModifier[indexOfLabelPressed]) * 100)) + "%");
		}else if(obj == countButton){
			countPopup.setLocation(700, 220);
			countPopup.setInvoker(countPopup);
			countPopup.setVisible(true);
		}
		
		// discount buttons
		if(indexOfLabelPressed != -1){ // if it is label
			okDiscountButton.setText("Set Price");
			if(obj == discountButton0 || obj == discountButton1 || obj == discountButton2 || obj == discountButton3 || obj == discountButton4
					|| obj == discountButton5 || obj == discountButton6 || obj == discountButton7 || obj == discountButton8 || obj == discountButton9){
				itemDiscountPopupString = itemDiscountPopupString + source;
				itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
			}else if(obj == clearDiscountButton){
				itemDiscountPopupString = "";
				itemDiscountPopupLabel.setText("Discount: " + itemCountPopupString);
				discountPopup.setVisible(false);
			}else if(obj == okDiscountButton){
				if(itemDiscountPopupString == ""){
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
					discountPopup.setVisible(false);
				}else{
					itemPriceOfLabel[indexOfLabelPressed] = Double.parseDouble(itemDiscountPopupString)/100;
					discountPopup.setVisible(false);
					setLabelText();
					setCost();
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
				}
			}else if(obj == percentDiscountButton){
				if(itemDiscountPopupString == ""){
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
					discountPopup.setVisible(false);
				}else{
					itemPriceModifier[indexOfLabelPressed] = (100 - Double.parseDouble(itemDiscountPopupString)) / 100;
					discountPopup.setVisible(false);
					setLabelText();
					setCost();
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
				}
			}else if(obj == resetDiscountButton){
				itemPriceModifier[indexOfLabelPressed] = 1;
				itemPriceOfLabel[indexOfLabelPressed] = itemOriginalPrice[indexOfLabelPressed];
				setLabelText();
				setCost();
				discountPopup.setVisible(false);
				itemDiscountPopupString = "";
				itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
			}
		}else if(indexOfLabelPressed == -1){
			okDiscountButton.setText("Deduct");
			if(obj == discountButton0 || obj == discountButton1 || obj == discountButton2 || obj == discountButton3 || obj == discountButton4
					|| obj == discountButton5 || obj == discountButton6 || obj == discountButton7 || obj == discountButton8 || obj == discountButton9){
				itemDiscountPopupString = itemDiscountPopupString + source;
				itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
			}else if(obj == clearDiscountButton){
				itemDiscountPopupString = "";
				itemDiscountPopupLabel.setText("Discount: " + itemCountPopupString);
				discountPopup.setVisible(false);
			}else if(obj == okDiscountButton){
				if(itemDiscountPopupString == ""){
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
					discountPopup.setVisible(false);
				}else{
					finalModifier = Double.parseDouble(itemDiscountPopupString)/100;
					discountPopup.setVisible(false);
					setCost();
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
				}
			}else if(obj == percentDiscountButton){
				if(itemDiscountPopupString == ""){
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
					discountPopup.setVisible(false);
				}else{
					finalPercentModifier = (100 - Double.parseDouble(itemDiscountPopupString)) / 100;
					discountPopup.setVisible(false);
					setCost();
					itemDiscountPopupString = "";
					itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
				}
			}else if(obj == resetDiscountButton){
				finalPercentModifier = 1;
				finalModifier = 0;
				setCost();
				discountPopup.setVisible(false);
				itemDiscountPopupString = "";
				itemDiscountPopupLabel.setText("Discount: " + itemDiscountPopupString);
			}
		}
		// count buttons
		if(obj == countButton0 || obj == countButton1 || obj == countButton2 || obj == countButton3 || obj == countButton4
				|| obj == countButton5 || obj == countButton6 || obj == countButton7 || obj == countButton8 || obj == countButton9){
			itemCountPopupString = itemCountPopupString + source;
			itemCountPopupLabel.setText("Item count: " + itemCountPopupString);
		}else if(obj == clearCountButton){
			itemCountPopupString = "";
			itemCountPopupLabel.setText("Item count: " + itemCountPopupString);
			countPopup.setVisible(false);
		}else if(obj == okCountButton){
			if(itemCountPopupString == ""){
				itemCountPopupString = "";
				itemCountPopupLabel.setText("Item count: " + itemCountPopupString);
				countPopup.setVisible(false);
			}else{
				itemCountOfLabel[indexOfLabelPressed] = Integer.parseInt(itemCountPopupString);
				countPopup.setVisible(false);
				setLabelText();
				setCost();
				itemCountPopupString = "";
				itemCountPopupLabel.setText("Item count: " + itemCountPopupString);
			}
		}
		
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
}
