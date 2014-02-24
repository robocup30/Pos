package com.josephchoi.pos;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.ValidityException;


public class StatisticTablePanelB extends JPanel implements TableModelListener, MouseListener{
	
	StatisticPanel statPanel;
	JTable table;
	Object[][] data;
	String[] columnNames = {"File Name", "Date", "Total", "Cash", "Visa", "Debit"};
	JScrollPane scrollPane;
	int xmlFileNumber;
	
	
	public StatisticTablePanelB(StatisticPanel in){
		
		statPanel = in;
		
		writeTableData();
		table = new JTable(data, columnNames){
	        public Class getColumnClass(int c) {
	        		return getValueAt(0, c).getClass();
	        }
	        public boolean isCellEditable(int row, int column) {
	            //all cells false
	            return false;
	            }
		};
		table.getModel().addTableModelListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1200, 900));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().toggleSortOrder(1);
		table.getRowSorter().toggleSortOrder(1);
		table.addMouseListener(this);
		
		add(scrollPane);
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
		redrawTable();
		System.out.println("fds");
		System.out.println(table.getSelectedRow());
	}
	
	
	public void redrawTable(){
		writeTableData();
		remove(scrollPane);
		table = new JTable(data, columnNames){
	        public Class getColumnClass(int c) {
	            return getValueAt(0, c).getClass();
	        }
	        
	        public boolean isCellEditable(int row, int column) {
	            //all cells false
	            return false;
	         }
		};
		table.getModel().addTableModelListener(this);
		scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(1200, 900));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoCreateRowSorter(true);
		table.getRowSorter().toggleSortOrder(1);
		table.getRowSorter().toggleSortOrder(1);
		table.addMouseListener(this);
		
		add(scrollPane);
		revalidate();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("fds");
		System.out.println(table.getSelectedRow());
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
