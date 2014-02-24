package com.josephchoi.pos;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class StatisticPanelStatPanel extends JPanel{
	
	StatisticPanel statPanel;
	JLabel totalCost;
	JLabel totalCash;
	JLabel totalVisa;
	JLabel totalDebit;
	double totalDouble;
	double cashDouble;
	double visaDouble;
	double debitDouble;
	DecimalFormat df = new DecimalFormat("#.##");
	
	
	public StatisticPanelStatPanel(StatisticPanel in){
		
		statPanel = in;
		totalCost = new JLabel();
		totalCash = new JLabel();
		totalVisa = new JLabel();
		totalDebit = new JLabel();
		
		setLayout(new GridLayout(1, 4));
		setLabels();
		add(totalCost);
		add(totalCash);
		add(totalVisa);
		add(totalDebit);
		
		
	}
	
	
	public void setLabels(){
		
		totalDouble = 0;
		cashDouble = 0;
		visaDouble = 0;
		debitDouble = 0;
		for(int i = 0; i < statPanel.statTablePanel.selectedRows.length; i++){
			totalDouble += (double) statPanel.statTablePanel.table.getValueAt(statPanel.statTablePanel.selectedRows[i], 2);
			cashDouble += (double) statPanel.statTablePanel.table.getValueAt(statPanel.statTablePanel.selectedRows[i], 3);
			visaDouble += (double) statPanel.statTablePanel.table.getValueAt(statPanel.statTablePanel.selectedRows[i], 4);
			debitDouble += (double) statPanel.statTablePanel.table.getValueAt(statPanel.statTablePanel.selectedRows[i], 5);
		}
		
		
		totalCost.setText("Total: $" + Double.parseDouble(df.format(totalDouble)));
		totalCash.setText("Total Cash: $" + Double.parseDouble(df.format(cashDouble)));
		totalVisa.setText("Total Visa: $" + Double.parseDouble(df.format(visaDouble)));
		totalDebit.setText("Total Debit: $" + Double.parseDouble(df.format(debitDouble)));
	}
	
	
	
}
