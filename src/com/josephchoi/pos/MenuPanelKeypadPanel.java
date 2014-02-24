package com.josephchoi.pos;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanelKeypadPanel extends JPanel implements ActionListener{
	
	GridLayout grid;
	
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
	JButton fnButton1;
	JButton fnButton2;
	
	
	public MenuPanelKeypadPanel(MenuPanel in){
		grid = new GridLayout(4, 3);
		
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
		fnButton1 = new JButton("Ok");
		fnButton2 = new JButton("Clear");
		
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
		fnButton1.addActionListener(this);
		fnButton2.addActionListener(this);
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		add(button7);
		add(button8);
		add(button9);
		add(fnButton1);
		add(button0);
		add(fnButton2);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
