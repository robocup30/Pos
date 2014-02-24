package com.josephchoi.pos;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


public class StatisticTablePanel extends JPanel implements TableModelListener, ActionListener{
	
	StatisticPanel statPanel;
	JTable table;
	Object[][] data;
	String[] columnNames = {"File Name", "Date", "Total", "Cash", "Visa", "Debit"};
	JScrollPane scrollPane;
	int xmlFileNumber;
	
	int[] selectedRows = new int[0];
	int[] selectedModelRows = new int[0];
	
	JButton deleteButton;
	
	
	public StatisticTablePanel(StatisticPanel in){
		
		statPanel = in;
		writeTableData();
		
		deleteButton = new JButton("Delete Selected Log");
		deleteButton.addActionListener(this);
		
		table = new JTable(new MyTableModel());
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1200, 900));
		table.setFillsViewportHeight(true);
		table.getModel().addTableModelListener(this);
		table.setAutoCreateRowSorter(true);
		
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getSelectionModel().addListSelectionListener(
		        new ListSelectionListener() {
		            public void valueChanged(ListSelectionEvent event) {
		                selectedRows = table.getSelectedRows();
		                selectedModelRows = new int[selectedRows.length];
		                if (selectedRows.length < 0) {
		                    //Selection got filtered away.
		                } else {
		                	for(int i = 0; i < selectedRows.length; i++){
			                    selectedModelRows[i] = table.convertRowIndexToModel(selectedRows[i]);
		                	}
		                }
		                
		                if (! event.getValueIsAdjusting()){
			                statPanel.statMidPanel.setLabels();
		                }
		            }
		        }
		);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(deleteButton, BorderLayout.SOUTH);
	}
	
	class MyTableModel extends AbstractTableModel{
		
		public String getColumnName(int col){
			return columnNames[col];
		}
		
		public Class<?> getColumnClass(int col){
			return data[0][col].getClass();
		}
		
		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return data[rowIndex][columnIndex];
		}
		
	    public void setValueAt(Object value, int row, int col) {
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
	    
	    public boolean isCellEditable(int row, int col) {
	    	return false;
	    }
	}
	public void writeTableData(){
		
		xmlFileNumber = 0;
		File logFiles = new File(statPanel.startup.filePath + "sales logs");
		File[] logFilesUnfiltered = logFiles.listFiles();
		String[] logFileNames = logFiles.list();
		String[] logFileNamesFiltered = new String[logFileNames.length];
		File[] logFilesFiltered = new File[logFileNames.length];
		for(int i = 0; i < logFileNames.length; i++){
			if(logFileNames[i].indexOf(".xml") > -1){
				logFileNamesFiltered[xmlFileNumber] = logFileNames[i];
				logFilesFiltered[xmlFileNumber] = logFilesUnfiltered[i];
				xmlFileNumber++;
			}
		}
		
		Date[] logFilesDates = new Date[xmlFileNumber];
		Double[] cash = new Double[xmlFileNumber];
		Double[] visa = new Double[xmlFileNumber];
		Double[] debit = new Double[xmlFileNumber];
		Double[] cashPaid = new Double[xmlFileNumber];
		Double[] visaPaid = new Double[xmlFileNumber];
		Double[] debitPaid = new Double[xmlFileNumber];
		Double[] cashRefund = new Double[xmlFileNumber];
		Double[] visaRefund = new Double[xmlFileNumber];
		Double[] debitRefund = new Double[xmlFileNumber];
		Double[] total = new Double[xmlFileNumber];
		
		for(int i = 0; i < xmlFileNumber; i++){
			cash[i] = 0.0;
			visa[i] = 0.0;
			debit[i] = 0.0;
			cashPaid[i] = 0.0;
			visaPaid[i] = 0.0;
			debitPaid[i] = 0.0;
			cashRefund[i] = 0.0;
			visaRefund[i] = 0.0;
			debitRefund[i] = 0.0;
		}
		
		DateFormat dfd = new SimpleDateFormat("yyyy/MM/dd");
		
		for(int i = 0; i < xmlFileNumber; i++){
			try {
				File logFile = logFilesFiltered[i];
				Builder builder = new Builder();
				Document doc = builder.build(logFile);
				
				Element root = doc.getRootElement();
				Element date = root.getFirstChildElement("date");
				Element sales = root.getFirstChildElement("sales");
				Elements saleElements = sales.getChildElements("sale");
				
				for(int j = 0; j < saleElements.size(); j++){
					Element sale = saleElements.get(j);
					Element cost = sale.getFirstChildElement("cost");
					String costString = cost.getValue();
					double costDouble = Double.parseDouble(costString);
					Element paymentMethod = sale.getFirstChildElement("paymentmethod");
					String paymentMethodString = paymentMethod.getValue();
					
					
					if(paymentMethodString.equals("Cash")){
						cashPaid[i] += costDouble;
					} else if (paymentMethodString.equals("Visa")){
						visaPaid[i] += costDouble;
					} else if (paymentMethodString.equals("Debit")){
						debitPaid[i] += costDouble;
					} else if (paymentMethodString.equals("Refund Cash")){
						cashRefund[i] += costDouble;
					} else if (paymentMethodString.equals("Refund Visa")){
						visaRefund[i] += costDouble;
					} else if (paymentMethodString.equals("Refund Debit")){
						debitRefund[i] += costDouble;
					}
					
					
				}
				
				cash[i] = cashPaid[i] - cashRefund[i];
				visa[i] = visaPaid[i] - visaRefund[i];
				debit[i] = debitPaid[i] - debitRefund[i];
				total[i] = cash[i] + visa[i] + debit[i];
				
				String tempDateString = date.getValue();
				
				try {
					logFilesDates[i] = dfd.parse(tempDateString);
				} catch (ParseException e) {
					e.printStackTrace();
				}

			} catch (ValidityException e) {
				e.printStackTrace();
			} catch (ParsingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		
		
		data = new Object[xmlFileNumber][6];
		for(int i = 0; i < xmlFileNumber; i++){
			data[i][0] = logFileNamesFiltered[i];
			
			data[i][1] = logFilesDates[i];
			
			data[i][2] = total[i];
			data[i][3] = cash[i];
			data[i][4] = visa[i];
			data[i][5] = debit[i];
		}
	}
	
	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < selectedRows.length; i++){
			File fileToDelete = new File(statPanel.startup.filePath + "sales logs\\" + data[selectedModelRows[i]][0]);
			fileToDelete.delete();
			writeTableData();
			revalidate();
			repaint();
		}
	}
	
}
