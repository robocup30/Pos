package com.josephchoi.pos;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeyPadPanel extends JPanel implements ActionListener{
	
	Startup startup;
	
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
	
	
	public KeyPadPanel(Startup in){
		
		startup = in;
		
		// setLookAndFeel();
		GridLayout grid = new GridLayout(4, 3);
		setLayout(grid);
		
		Font keyPadButtonFont = new Font("Dialog", Font.ITALIC, 24);
		
		// set Button Text
		button0 = new JButton("0");
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
		
		// set Font
		button0.setFont(keyPadButtonFont);
		button1.setFont(keyPadButtonFont);
		button2.setFont(keyPadButtonFont);
		button3.setFont(keyPadButtonFont);
		button4.setFont(keyPadButtonFont);
		button5.setFont(keyPadButtonFont);
		button6.setFont(keyPadButtonFont);
		button7.setFont(keyPadButtonFont);
		button8.setFont(keyPadButtonFont);
		button9.setFont(keyPadButtonFont);
		okButton.setFont(keyPadButtonFont);
		clearButton.setFont(keyPadButtonFont);
		
		button0.setPreferredSize(new Dimension(100, 130));
		
		// add action listener to buttons
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
		
		// add buttons to the Panel
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		add(button7);
		add(button8);
		add(button9);
		add(okButton);
		add(button0);
		add(clearButton);
		
		
		setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		
		char[] passChar = startup.passwordPanel.pass.getPassword();
		String passString = new String(passChar);
		
		// if number is pressed type that in to password field
		if(source == "0" || source == "1" || source == "2" || source == "3" || source == "4"
				|| source == "5" || source == "6" || source == "7" || source == "8" || source == "9"){
			startup.passwordPanel.pass.getPassword();
			String newPassString = passString + source;
			startup.passwordPanel.pass.setText(newPassString);
		}
		
		// if clear is pressed, clear password field
		if(source == "Clear"){
			startup.passwordPanel.pass.setText("");
		}
		
		if(source == "Ok" && passString.equals(startup.passwordPanel.userSetPassword)){
			startup.passwordPanel.passwordStatusLabel.setText("");
			startup.passwordPanel.pass.setText("");
			startup.remove(startup.startupPanel);
			startup.passwordPanel.invalidate();
			startup.setLayout(startup.border);
			// startup.add(startup.menuBarPanel, BorderLayout.NORTH);
			startup.setJMenuBar(startup.menuBarPanel);
			startup.validate();
			startup.repaint();
		}
		
		if(source == "Ok" && false == passString.equals(startup.passwordPanel.userSetPassword)){
			startup.passwordPanel.passwordStatusLabel.setText("Incorrect Password");
			startup.passwordPanel.invalidate();
			startup.validate();
			startup.repaint();
		}
		
		invalidate();
		validate();
		repaint();
		
	}
	
	public Insets getInsets(){
		Insets squeeze = new Insets(100, 50, 50, 50);
		return squeeze;	
	}
	
	
}
