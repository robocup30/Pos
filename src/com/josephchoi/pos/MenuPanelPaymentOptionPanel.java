package com.josephchoi.pos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuPanelPaymentOptionPanel extends JPanel implements ActionListener{
	
	
	MenuPanel menuPanel;
	
	JButton cashButton;
	JButton visaButton;
	JButton debitButton;
	JButton refundCashButton;
	JButton refundVisaButton;
	JButton refundDebitButton;
	
	JPopupMenu paymentPanelPopup;
	FlowLayout paymentPanelLayout;
	
	JPanel keypadPanel;
	GridLayout keypadLayout;
	JButton keypadButton0;
	JButton keypadButton1;
	JButton keypadButton2;
	JButton keypadButton3;
	JButton keypadButton4;
	JButton keypadButton5;
	JButton keypadButton6;
	JButton keypadButton7;
	JButton keypadButton8;
	JButton keypadButton9;
	JButton okKeypadButton;
	JButton clearKeypadButton;
	
	JPanel exactPanel;
	GridLayout exactLayout;
	JButton exact10;
	JButton exact20;
	JButton exact50;
	JButton exactAmount;
	
	JPanel totalPanel;
	GridLayout totalLayout;
	JLabel totalLabel;
	String keypadPressedString;
	double keypadPressedDouble;
	JLabel totalAmountLabel;
	JLabel paidLabel;
	JLabel paidAmountLabel;
	JLabel spaceMaker;
	JLabel notEnough;
	JLabel paymentType;
	
	String paymentOptionPressed;
	
	SalesLogWriter salesLogWriter;
	
	
	public MenuPanelPaymentOptionPanel(MenuPanel in){
		
		menuPanel = in;
		paymentOptionPressed = "";
		
		salesLogWriter = new SalesLogWriter(this);
		
		GridLayout grid = new GridLayout(6, 1);
		setLayout(grid);
		cashButton = new JButton("Cash");
		cashButton.setPreferredSize(new Dimension(100, 100));
		visaButton = new JButton("Visa");
		debitButton = new JButton("Debit");
		refundCashButton = new JButton("Refund Cash");
		refundVisaButton = new JButton("Refund Visa");
		refundDebitButton = new JButton("Refund Debit");
		
		cashButton.addActionListener(this);
		visaButton.addActionListener(this);
		debitButton.addActionListener(this);
		refundCashButton.addActionListener(this);
		refundVisaButton.addActionListener(this);
		refundDebitButton.addActionListener(this);
		
		// set up paymentPanel
		paymentPanelPopup = new JPopupMenu();
		paymentPanelLayout = new FlowLayout();
		paymentPanelPopup.setLayout(paymentPanelLayout);
		paymentPanelLayout.setHgap(30);
		keypadPanel = new JPanel();
		keypadLayout = new GridLayout(4, 3);
		keypadPanel.setLayout(keypadLayout);
		
		// set up total Panel
		totalPanel = new JPanel();
		totalLayout = new GridLayout(5,1);
		totalPanel.setLayout(totalLayout);
		totalLayout.setVgap(40);
		keypadPressedString = "";
		notEnough = new JLabel("");
		keypadPressedDouble = .00;
		spaceMaker = new JLabel("                                                 ");
		totalLabel = new JLabel("Total: .00");
		totalLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		// totalAmountLabel = new JLabel(".00");
		paidLabel = new JLabel("Paid: .00");
		paidLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		// paidAmountLabel = new JLabel(".00");
		paymentType = new JLabel("");
		
		totalPanel.add(paymentType);
		totalPanel.add(spaceMaker);
		totalPanel.add(totalLabel);
		totalPanel.add(paidLabel);
		totalPanel.add(notEnough);
		
		paymentPanelPopup.add(totalPanel);
		
		// set up keypad panel
		keypadButton0 = new JButton("0");
		keypadButton0.setPreferredSize(new Dimension(90, 90));
		keypadButton1 = new JButton("1");
		keypadButton2 = new JButton("2");
		keypadButton3 = new JButton("3");
		keypadButton4 = new JButton("4");
		keypadButton5 = new JButton("5");
		keypadButton6 = new JButton("6");
		keypadButton7 = new JButton("7");
		keypadButton8 = new JButton("8");
		keypadButton9 = new JButton("9");
		okKeypadButton = new JButton("Ok");
		clearKeypadButton = new JButton("Cancel");
		
		keypadButton0.addActionListener(this);
		keypadButton1.addActionListener(this);
		keypadButton2.addActionListener(this);
		keypadButton3.addActionListener(this);
		keypadButton4.addActionListener(this);
		keypadButton5.addActionListener(this);
		keypadButton6.addActionListener(this);
		keypadButton7.addActionListener(this);
		keypadButton8.addActionListener(this);
		keypadButton9.addActionListener(this);
		okKeypadButton.addActionListener(this);
		clearKeypadButton.addActionListener(this);
		
		
		keypadPanel.add(keypadButton1);
		keypadPanel.add(keypadButton2);
		keypadPanel.add(keypadButton3);
		keypadPanel.add(keypadButton4);
		keypadPanel.add(keypadButton5);
		keypadPanel.add(keypadButton6);
		keypadPanel.add(keypadButton7);
		keypadPanel.add(keypadButton8);
		keypadPanel.add(keypadButton9);
		keypadPanel.add(okKeypadButton);
		keypadPanel.add(keypadButton0);
		keypadPanel.add(clearKeypadButton);
		
		paymentPanelPopup.add(keypadPanel);
		
		// set up exact panel
		exactPanel = new JPanel();
		exactLayout = new GridLayout(4, 1);
		exactLayout.setVgap(25);
		exactPanel.setLayout(exactLayout);
		exact10 = new JButton("$10");
		exact10.setPreferredSize(new Dimension(80, 80));
		exact20 = new JButton("$20");
		exact50 = new JButton("$50");
		exactAmount = new JButton("Exact amount");
		
		exact10.addActionListener(this);
		exact20.addActionListener(this);
		exact50.addActionListener(this);
		exactAmount.addActionListener(this);
		
		exactPanel.add(exact10);
		exactPanel.add(exact20);
		exactPanel.add(exact50);
		exactPanel.add(exactAmount);
		
		paymentPanelPopup.add(exactPanel);
		
		
		add(cashButton);
		add(visaButton);
		add(debitButton);
		add(refundCashButton);
		add(refundVisaButton);
		add(refundDebitButton);
		
		
		
		
	}
	
	
	public void setChange(){
		menuPanel.change = menuPanel.paid - menuPanel.costAfterTax;
		menuPanel.changeString = menuPanel.df.format(menuPanel.change);
		menuPanel.orderPanel.changeLabel.setText("Change: " + menuPanel.changeString);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object obj = e.getSource();

		if(obj == cashButton || obj == visaButton || obj == debitButton || obj == refundCashButton || obj == refundVisaButton || obj == refundDebitButton){
			paymentPanelPopup.setLocation(700, 220);
			paymentPanelPopup.setInvoker(paymentPanelPopup);
			paymentPanelPopup.setVisible(true);
			
			paymentOptionPressed = e.getActionCommand();
			
			paymentType.setText(paymentOptionPressed);
			
			if (menuPanel.costAfterTaxString != null){
				totalLabel.setText("Total: " + menuPanel.costAfterTaxString);
			}
			
		}
		
		if(obj == exact10){
			keypadPressedString = "";
			menuPanel.paid = 10.00;
			menuPanel.paidString = "10.00";
			paidLabel.setText("Paid: 10.00");
		}else if(obj == exact20){
			keypadPressedString = "";
			menuPanel.paid = 20.00;
			menuPanel.paidString = "20.00";
			paidLabel.setText("Paid: 20.00");
		}else if(obj == exact50){
			keypadPressedString = "";
			menuPanel.paid = 50.00;
			menuPanel.paidString = "50.00";
			paidLabel.setText("Paid: 50.00");
		}else if(obj == exactAmount){
			keypadPressedString = "";
			menuPanel.paid = menuPanel.costAfterTax;
			menuPanel.paidString = menuPanel.costAfterTaxString;
			paidLabel.setText("Paid: " + menuPanel.paidString);
		}
		
		if(obj == keypadButton0 || obj == keypadButton1 || obj == keypadButton2 || obj == keypadButton3 || obj == keypadButton4
				|| obj == keypadButton5 || obj == keypadButton6 || obj == keypadButton7 || obj == keypadButton8 || obj == keypadButton9){
			
			if(keypadPressedString.length() < 8){
				keypadPressedString = keypadPressedString + e.getActionCommand();
				menuPanel.paid = Double.parseDouble(keypadPressedString) / 100;
				menuPanel.paidString = menuPanel.df.format(menuPanel.paid);
				paidLabel.setText("Paid: " + menuPanel.paidString);
			}
		}
		
		if(obj == okKeypadButton){
			if(menuPanel.paid >= menuPanel.costAfterTax){
				if(paymentOptionPressed == "Cash"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}else if(paymentOptionPressed == "Debit"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}else if(paymentOptionPressed == "Visa"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}else if(paymentOptionPressed == "Refund Cash"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}else if(paymentOptionPressed == "Refund Visa"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}else if(paymentOptionPressed == "Refund Debit"){
					
					menuPanel.orderPanel.printClass.printBoth();
					
					salesLogWriter.writeLog();
					setChange();
					paymentPanelPopup.setVisible(false);
					menuPanel.orderPanel.resetAll();
					keypadPressedString = "";
					paidLabel.setText("Paid: .00");
					notEnough.setText("");
				}
				paymentType.setText("");
				
			}else if(menuPanel.paid < menuPanel.costAfterTax){
				notEnough.setText("Not enough payment");
			}
		}
		
		if(obj == clearKeypadButton){
			keypadPressedString = "";
			paidLabel.setText("Paid: .00");
			paymentPanelPopup.setVisible(false);
			notEnough.setText("");
			paymentType.setText("");
		}
	}

}
