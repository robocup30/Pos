package com.josephchoi.pos;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MenuBar extends JMenuBar implements ActionListener{
	
	Startup startup;
	
	JMenuBar menuBar;
	
	JMenu file;
	JMenu options;
	
	JMenuItem itemMenu;
	JMenuItem statistics;
	JMenuItem lock;
	JMenuItem passwordChange;
	JMenuItem changeMenu;
	
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	
	MenuBar(Startup in){
		startup = in;
		
		menuBar = new JMenuBar();
		
        file = new JMenu("File");
        file.setPreferredSize(new Dimension(100, 50));
		file.setMnemonic(KeyEvent.VK_F);
        menuBar.add(file);
        options = new JMenu("Options");
        options.setPreferredSize(new Dimension(100, 50));
		options.setMnemonic(KeyEvent.VK_O);
		menuBar.add(options);
		
		itemMenu = new JMenuItem("Item menu");
        itemMenu.setPreferredSize(new Dimension(100, 50));
        itemMenu.addActionListener(this);
		file.add(itemMenu);
		statistics = new JMenuItem("Statistics");
		statistics.setPreferredSize(new Dimension(100, 50));
        statistics.addActionListener(this);
		file.add(statistics);
		lock = new JMenuItem("Lock system");
		lock.setPreferredSize(new Dimension(100, 50));
        lock.addActionListener(this);
		file.add(lock);
		
		
		passwordChange = new JMenuItem("Change password");
		passwordChange.setPreferredSize(new Dimension(150, 50));
        passwordChange.addActionListener(this);
        options.add(passwordChange);
        changeMenu = new JMenuItem("Change menu");
        changeMenu.setPreferredSize(new Dimension(100, 50));
        changeMenu.addActionListener(this);
        options.add(changeMenu);
        
        
		add(menuBar);
		
	}

	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		
		if (source.equals("Lock system")){
			startup.setJMenuBar(null);
			startup.setLayout(startup.flo);
			startup.add(startup.startupPanel);
			startup.remove(startup.menuPanel);
			startup.remove(startup.changeMenuPanel);
			startup.remove(startup.changePasswordPanel);
			startup.remove(startup.statisticPanel);
			startup.passwordPanel.setPassword();
			startup.revalidate();
			startup.repaint();
		}
		if(source.equals("Item menu")){
			startup.remove(startup.changeMenuPanel);
			startup.remove(startup.statisticPanel);
			startup.remove(startup.changePasswordPanel);
			startup.add(startup.menuPanel, BorderLayout.CENTER);

			startup.revalidate();
			startup.repaint();
		}
		
		if(source.equals("Statistics")){
			startup.remove(startup.changeMenuPanel);
			startup.remove(startup.menuPanel);
			startup.remove(startup.changePasswordPanel);
			
			startup.statisticPanel.statTablePanel.writeTableData();
			startup.add(startup.statisticPanel, BorderLayout.CENTER);
			
			
			startup.revalidate();
			startup.repaint();
		}
		
		if(source.equals("Change menu")){
			startup.remove(startup.menuPanel);
			startup.remove(startup.statisticPanel);
			startup.remove(startup.changePasswordPanel);
			
			startup.add(startup.changeMenuPanel, BorderLayout.CENTER);

			startup.revalidate();
			startup.repaint();
		}
		
		if(source.equals("Change password")){
			startup.remove(startup.menuPanel);
			startup.remove(startup.statisticPanel);
			startup.remove(startup.changeMenuPanel);
			startup.changePasswordPanel.initalize();
			startup.add(startup.changePasswordPanel, BorderLayout.CENTER);
			
			startup.revalidate();
			startup.repaint();
		}
	}
	
	
}
