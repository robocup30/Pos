package com.josephchoi.pos;
import java.awt.BorderLayout;

import javax.swing.JPanel;


public class StatisticPanel extends JPanel{
	
	Startup startup;
	StatisticTablePanel statTablePanel;
	StatisticPanelStatPanel statMidPanel;
	
	
	BorderLayout statisticLayout;
	
	
	public StatisticPanel(Startup in){
		
		
		startup = in;
		
		statTablePanel = new StatisticTablePanel(this);
		statMidPanel = new StatisticPanelStatPanel(this);
		
		statisticLayout = new BorderLayout();
		setLayout(statisticLayout);
		
		add(statTablePanel, BorderLayout.NORTH);
		add(statMidPanel, BorderLayout.CENTER);
		
	}
	
	
}
