package com.josephchoi.pos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;
import nu.xom.Serializer;
import nu.xom.ValidityException;


public class ChangeMenuPanelB extends JPanel implements ActionListener, TableModelListener{
	
	Startup startup;
	
	String[] columnNames = {"#", "Name", "Cost", "Button color(RGB)"};
	JTable table;
	JButton save;
	
	Document doc;
	
	String[] redString;
	String[] greenString;
	String[] blueString;
	
	public ChangeMenuPanelB(Startup in){
		
		startup = in;
		setLayout(new BorderLayout());
		save = new JButton("Save");
		save.addActionListener(this);
		
		Object[][] data;
		
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        
		try {
			File propertiesFile = new File(startup.filePath + "properties.xml");
			Builder builder = new Builder();
			Document doc = builder.build(propertiesFile);
			
			Element root = doc.getRootElement();
			Element menu = root.getFirstChildElement("menu");
			Elements menuItems = menu.getChildElements("menuItem");
			
			data = new Object[menuItems.size()][4];
			redString = new String[menuItems.size()];
			greenString = new String[menuItems.size()];
			blueString = new String[menuItems.size()];
			
			table = new JTable(data, columnNames){
				  public boolean isCellEditable(int row, int column){  
					    if(column == 0) return false;  
					    return true;  
					  }
				  
			        public Class getColumnClass(int c) {
			            return getValueAt(0, c).getClass();
			        }
			};
			
			for(int i = 0; i < menuItems.size(); i++){
				Element menuItem = menuItems.get(i);
				Element name = menuItem.getFirstChildElement("name");
				Element price = menuItem.getFirstChildElement("price");
				Element color = menuItem.getFirstChildElement("color");
				Element red = color.getFirstChildElement("red");
				Element green = color.getFirstChildElement("green");
				Element blue = color.getFirstChildElement("blue");
				String nameString = name.getValue();
				String priceString = price.getValue();
				double priceDouble = Double.parseDouble(priceString);
				redString[i] = red.getValue();
				greenString[i] = green.getValue();
				blueString[i] = blue.getValue();
				data[i][0] = i+1;
				data[i][1] = nameString;
				data[i][2] = priceString;
				data[i][3] = redString[i] + ", " + greenString[i] + ", " + blueString[i];
	            new ButtonTableCellRenderer(table, 3, i); 
			}

			
		} catch (ValidityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParsingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table.setPreferredScrollableViewportSize(new Dimension(900, 800));
		table.setFillsViewportHeight(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getModel().addTableModelListener(this);
		// table.setRowSelectionAllowed(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		add(save, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		try {
			File propertyFile = new File(startup.filePath + "properties.xml");
			Builder builder = new Builder();
			doc = builder.build(propertyFile);
			
			Element root = doc.getRootElement();
			Element menu = root.getFirstChildElement("menu");
			Elements menuItems = menu.getChildElements();
			
			for(int i = 0; i < 99; i++){
				Element menuItem = menuItems.get(i);
				Element itemNumber = menuItem.getFirstChildElement("itemName");
				Element name = menuItem.getFirstChildElement("name");
				Element price = menuItem.getFirstChildElement("price");
				Element color = menuItem.getFirstChildElement("color");
				Element red = color.getFirstChildElement("red");
				Element green = color.getFirstChildElement("green");
				Element blue = color.getFirstChildElement("blue");
				String colorString = (String) table.getValueAt(i, 3);
				String[] colorStringArray = colorString.split(", ");
				
				name.removeChildren();
				name.appendChild((String) table.getValueAt(i, 1));
				price.removeChildren();
				price.appendChild((String) table.getValueAt(i, 2));
				
				red.removeChildren();
				red.appendChild(colorStringArray[0]);
				green.removeChildren();
				green.appendChild(colorStringArray[1]);
				blue.removeChildren();
				blue.appendChild(colorStringArray[2]);
				
			}
			
			
			FileOutputStream propOutStream = new FileOutputStream(propertyFile);
	        Serializer serializer = new Serializer(propOutStream, "ISO-8859-1");
	        serializer.setIndent(4);
	        serializer.setMaxLength(150);
	        serializer.write(doc);
	        propOutStream.close();
	        

			
		} catch (ValidityException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (ParsingException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
        
	}

	class ButtonTableCellRenderer implements TableCellRenderer{  

		JTable table;  
		JButton renderButton;  
		JButton editButton;  
		String text;  

		public ButtonTableCellRenderer(JTable table, int column, int row)  {  
			super();
			this.table = table;  
			renderButton = new JButton(); 
			TableColumnModel columnModel = table.getColumnModel();  
			columnModel.getColumn(column).setCellRenderer(this); 
			// columnModel.getColumn(column).setCellEditor( this );  
		}  



		public Component getTableCellRendererComponent(JTable table,  
				Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
			// TODO Auto-generated method stub
			
			Color buttonColor = new Color(Integer.parseInt(redString[row]), Integer.parseInt(greenString[row]), Integer.parseInt(blueString[row]));
			
			renderButton.setBackground(buttonColor);  
			renderButton.setBorderPainted(false);  
			renderButton.setText(redString[row] + ", " + greenString[row] + ", " + blueString[row]);
			// renderButton.setIcon(new ImageIcon("Obs.gif"));  
			return renderButton;  
		}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        TableModel model = (TableModel)e.getSource();
        String columnName = model.getColumnName(column);
        Object data = model.getValueAt(row, column);

        
        
	}
}


