package com.josephchoi.pos;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import nu.xom.*;


public class MenuPanelItemButtonPanel extends JPanel implements ActionListener {

	MenuPanel menuPanel;
	
	JButton[] itemButton = new JButton[300];   // item buttons
	String[] priceString = new String[300]; // item price
	String[] nameString = new String[300]; // item name
	String[] nameStringReplaced = new String[300];
	
	int indexOfButtonPressed;
	
	
	public MenuPanelItemButtonPanel(MenuPanel in){
		
		menuPanel = in;
		GridLayout grid = new GridLayout(11, 9);
		setLayout(grid);
		
		// setLookAndFeel();
		setMenuButtons();
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// get the button pressed
		for(int i = 0; i < itemButton.length; i++){
			if(e.getSource() == itemButton[i]){
				indexOfButtonPressed = i;
			}
		}
		
		String str = "";
		menuPanel.orderPanel.orderLabel[menuPanel.orderPanel.counter] = new JLabel("");
		menuPanel.orderPanel.orderLabel[menuPanel.orderPanel.counter].addMouseListener(menuPanel.orderPanel);
		menuPanel.orderPanel.itemCountOfLabel[menuPanel.orderPanel.counter] = 1;
		menuPanel.orderPanel.itemPriceModifier[menuPanel.orderPanel.counter] = 1;
		menuPanel.orderPanel.itemOriginalPrice[menuPanel.orderPanel.counter] = Double.parseDouble(priceString[indexOfButtonPressed]);
		menuPanel.orderPanel.itemPriceOfLabel[menuPanel.orderPanel.counter] = Double.parseDouble(priceString[indexOfButtonPressed]);
		menuPanel.orderPanel.itemNameOfLabel[menuPanel.orderPanel.counter] = nameString[indexOfButtonPressed];
	    str = menuPanel.df.format(menuPanel.orderPanel.itemPriceOfLabel[menuPanel.orderPanel.counter]);
		menuPanel.orderPanel.orderLabel[menuPanel.orderPanel.counter].setText("   " + menuPanel.orderPanel.itemCountOfLabel[menuPanel.orderPanel.counter] + " " + menuPanel.orderPanel.itemNameOfLabel[menuPanel.orderPanel.counter]
				+ " $ " +  str);
		
		menuPanel.orderPanel.midPanel.add(menuPanel.orderPanel.orderLabel[menuPanel.orderPanel.counter]);
		menuPanel.revalidate();
		menuPanel.repaint();
		menuPanel.orderPanel.counter++;
		
		menuPanel.orderPanel.setCost();
		str = "";
		menuPanel.orderPanel.changeLabel.setText("Change: .00");
	}
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }
    
    public void setMenuButtons(){
		try {

			File propertiesFile = new File(menuPanel.startup.filePath + "properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element rootElement = doc.getRootElement();
			Element menuElement = rootElement.getFirstChildElement("menu");
			Elements menuItemsElements = menuElement.getChildElements("menuItem");
			int menuItemSize = menuItemsElements.size();
			
			for(int i = 0; i < menuItemSize; i++){
				Element menuItem = menuItemsElements.get(i);
				Element name = menuItem.getFirstChildElement("name");
				Element price = menuItem.getFirstChildElement("price");
				Element color = menuItem.getFirstChildElement("color");
				Element red = color.getFirstChildElement("red");
				Element green = color.getFirstChildElement("green");
				Element blue = color.getFirstChildElement("blue");
				
				String redString = red.getValue();
				String greenString = green.getValue();
				String blueString = blue.getValue();
				int redValue = 180;
				int greenValue = 180;
				int blueValue = 180;
				if(redString != ""){
					redValue = Integer.parseInt(redString);
					greenValue = Integer.parseInt(greenString);
					blueValue = Integer.parseInt(blueString);
				}
				Color buttonColor = new Color(redValue, greenValue, blueValue);
				if(name.getValue() != ""){
					nameString[i] = name.getValue();
					if(nameString[i].length() > 10){
						nameString[i] = "<html><div style=\"text-align: center;\">" + nameString[i] + "<html />";  // wraps text
					}
					priceString[i] = price.getValue();
					itemButton[i] = new JButton(nameString[i]);
					itemButton[i].setFont(new Font("Arial", Font.PLAIN, 17));
					itemButton[i].setPreferredSize(new Dimension(95, 75));
					itemButton[i].setBackground(buttonColor);
					itemButton[i].addActionListener(this);
					itemButton[i].setMargin(new Insets(0,0,0,0));
					add(itemButton[i]);
				}else{
					
					nameString[i] = name.getValue();
					priceString[i] = price.getValue();
					itemButton[i] = new JButton(nameString[i]);
					itemButton[i].setBackground(buttonColor);
					itemButton[i].setPreferredSize(new Dimension(95, 75));
					itemButton[i].setMargin(new Insets(0,0,0,0));
					// add(itemButton[i]);
					
				}
				
				nameString[i] = nameString[i].replaceAll("<html><div style=\"text-align: center;\">", "");
				nameString[i] = nameString[i].replaceAll("<html />", "");
			}

		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    public void resetButtons(){
    	for(int i = 0; i < 99; i++){
    		remove(itemButton[i]);
    	}
    	setMenuButtons();
    }
    
}


