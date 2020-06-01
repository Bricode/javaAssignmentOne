package passignmentone;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * @author Blake Chalmers
 * The purpose of this project was to make a gui capable of displaying and sorting an imported csv file.
 *	This was created during the year 2020
 */
public class GoogleStockPrices extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel TabOne;
	private JScrollPane scrollPane;
	private JTable table;
	private ArrayList<Google> data;
	private int searchValue;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoogleStockPrices frame = new GoogleStockPrices();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoogleStockPrices() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 594, 490);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		contentPane.add(tabbedPane);
		
		TabOne = new JPanel();
		tabbedPane.addTab("All results", null, TabOne, null);
		TabOne.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 547, 403);
		TabOne.add(scrollPane);
		
		table = new JTable();
		table.setModel(tm);
		scrollPane.setViewportView(table);
		//creating a combo box
		sortComboBox = new JComboBox();
		sortComboBox.setBounds(10, 419, 219, 22);
		sortComboBox.addItem("Date");
		sortComboBox.addItem("Open");
		sortComboBox.addItem("Close");
		sortComboBox.addItem("High");
		sortComboBox.addItem("Low");
		sortComboBox.addItem("AdjClose");
		sortComboBox.addItem("Volume");
		TabOne.add(sortComboBox);
		
		btnNewButton_2 = new JButton("Sort(Ascending)");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilities.sortAscList(data, sortComboBox.getSelectedItem().toString());
				Utilities.drawTable(data, tm);
			}
		});
		btnNewButton_2.setBounds(239, 419, 154, 23);
		TabOne.add(btnNewButton_2);
		
		btnNewButton_5 = new JButton("Sort(Descending)");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilities.sortDescList(data, sortComboBox.getSelectedItem().toString());
				Utilities.drawTable(data, tm);
			}
		});
		btnNewButton_5.setBounds(403, 419, 154, 23);
		TabOne.add(btnNewButton_5);
		
		panel = new JPanel();
		tabbedPane.addTab("Search", null, panel, null);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 5, 470, 341);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(search);
		scrollPane_1.setViewportView(table_1);
		
		btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchValue = 0;
				Utilities.createSingleResult( search, data,searchValue);
			}
		});
		btnNewButton.setBounds(10, 390, 89, 23);
		panel.add(btnNewButton);
		
		btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if (searchValue != 0) {
				searchValue --;
				Utilities.createSingleResult(search,data,searchValue);
			}			
		}
		});
		btnPrevious.setBounds(109, 424, 89, 23);
		panel.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (searchValue < 2334) {
				searchValue ++;
				Utilities.createSingleResult( search, data, searchValue);
			}
		}});
		btnNext.setBounds(10, 424, 89, 23);
		panel.add(btnNext);
		
		btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchValue = 2334;
				Utilities.createSingleResult( search, data, searchValue);
			}
		});
		btnLast.setBounds(109, 390, 89, 23);
		panel.add(btnLast);
		
		textField = new JTextField();
		textField.setBounds(10, 357, 188, 20);
		panel.add(textField);
		textField.setColumns(10);		
		
		searchComboBox = new JComboBox();
		searchComboBox.addItem("Date");
		searchComboBox.addItem("Open");
		searchComboBox.addItem("Close");
		searchComboBox.addItem("High");
		searchComboBox.addItem("Low");
		searchComboBox.addItem("AdjClose");
		searchComboBox.addItem("Volume");
		searchComboBox.setBounds(208, 357, 89, 22);
		panel.add(searchComboBox);
		
		btnNewButton_1 = new JButton("Search");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//as long as the returned int is positive, run and set searchvalue
				if(Utilities.searchData(data, searchComboBox.getSelectedItem().toString(), search, textField.getText()) != -1) {
					searchValue = Utilities.searchData(data, searchComboBox.getSelectedItem().toString(), search, textField.getText());
				}
				else JOptionPane.showMessageDialog(null, "Result not found");
				
			}
		});
		
		btnNewButton_1.setBounds(307, 357, 89, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton_3 = new JButton("Maximum");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilities.findMax(data, searchComboBox.getSelectedItem().toString(), search);
			}
		});
		
		btnNewButton_3.setBounds(208, 390, 89, 23);
		panel.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Minimum");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilities.findMini(data, searchComboBox.getSelectedItem().toString(), search);
			}
		});
		btnNewButton_4.setBounds(208, 424, 89, 23);
		panel.add(btnNewButton_4);
		
		data = new ArrayList<Google>();
		Utilities.readCSV(data);
		Utilities.drawTable(data, tm); 
		Utilities.createAPieGraph(data, tabbedPane);
		Utilities.createABarGraph(data, tabbedPane);
	}
	
	private DefaultTableModel tm = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "open", "high", "low", "close", "adjClose", "volume"
			}
		);
	
	private DefaultTableModel search = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Date", "open", "high", "low", "close", "adjClose", "volume"
			}
		);
	
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JButton btnNewButton;
	private JButton btnPrevious;
	private JButton btnNext;
	private JButton btnLast;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JComboBox searchComboBox;
	private JComboBox sortComboBox;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	
}
