package passignmentone;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


/**
 * <h1> Utilities <h1>
 * The Utilities class holds all methods relating to the calculation and generation of data in the GoogleStockPrices class. 
 * @author Blake C
 *
 */
public class Utilities {
	/**
	 * <h1> readCSV <h1>
	 * This methods reads in the data from a file in the format of a csv,
	 * It then uses the scanner method to split the file and the parse 
	 * those fields into an arrayList of type Google
	 * @param data This passes in the shared arrayList
	 * @return data This passes out the shared arrayList
	 */
	public static ArrayList<Google> readCSV(ArrayList<Google> data) {
		String date;
		Double open;
		Double high;
		Double low;
		Double close;
		Double adjClose;
		Long volume;
		String line;
		//The try catch loop for the scanner, ensures that there is no reading errors
		try {
			Scanner scanner = new Scanner(new File("GOOGL.csv"));
			while((scanner.hasNextLine())) {
				line = scanner.nextLine();
				String[] fields = line.split(",");
				date=fields[0];
				open=Double.parseDouble(fields[1]);
				high=Double.parseDouble(fields[2]);
				low=Double.parseDouble(fields[3]);
				close=Double.parseDouble(fields[4]);
				adjClose=Double.parseDouble(fields[5]);
				volume=Long.parseLong(fields[6]);
				data.add(new Google(date, open, high, low, close, adjClose, volume));				
			}			
		} catch (IOException e) {
			System.out.println("An exception has occured");
		};
		return data;		
	}
	/**
	 * This method references the data arraylist and then uses that all of the information to draw a table
	 * @param data The shared arrayList
	 * @param tm the jtable location to output the table
	 */
	public static void drawTable(ArrayList<Google> data, DefaultTableModel tm) {
		tm.setRowCount(0);		
		for(int i = 0; i < 2335; i++) {
			Object[] object = new Object[7];
			object[0] = data.get(i).getDate();
			object[1] = data.get(i).getOpen();
			object[2] = data.get(i).getHigh();
			object[3] = data.get(i).getLow();
			object[4] = data.get(i).getClose();
			object[5] = data.get(i).getAdjClose();
			object[6] = data.get(i).getVolume();		
			
			tm.addRow(object);
		}		
	}

	/**
	 * This method is to create the bar graph tab
	 * @param data is the shared arrayList
	 * @param tabbedPane the target where the bar graph is to be displayed
	 */
	public static void createABarGraph(ArrayList<Google> data, JTabbedPane tabbedPane) {
		DefaultCategoryDataset  input = new DefaultCategoryDataset ();
		long totalVolume2009 = 0;
		long totalVolume2010 = 0;
		long totalVolume2011 = 0;
		long totalVolume2012 = 0;
		long totalVolume2013 = 0;
		long totalVolume2014 = 0;
		long totalVolume2015 = 0;
		long totalVolume2016 = 0;
		long totalVolume2017 = 0;
		long totalVolume2018 = 0;
		
		for(int i =0; i < data.size() - 1; i++) {
			switch(data.get(i).getDate().substring(0,4)) {
			case "2009":
				totalVolume2009 += data.get(i).getVolume();
			case "2010":
				totalVolume2010 += data.get(i).getVolume();
			case "2011":
				totalVolume2011 += data.get(i).getVolume();
			case "2012":
				totalVolume2012 += data.get(i).getVolume();
			case "2013":
				totalVolume2013 += data.get(i).getVolume();
			case "2014":
				totalVolume2014 += data.get(i).getVolume();
			case "2015":
				totalVolume2015 += data.get(i).getVolume();
			case "2016":
				totalVolume2016 += data.get(i).getVolume();
			case "2017":
				totalVolume2017 += data.get(i).getVolume();
			case "2018":
				totalVolume2018 += data.get(i).getVolume();
			}			
		}	
		
		input.addValue(totalVolume2009 , "Volume of stock 2009", "Volume of stock 2009");
		input.addValue(totalVolume2010 , "Volume of stock 2010", "Volume of stock 2010");
		input.addValue(totalVolume2011 , "Volume of stock 2011", "Volume of stock 2011");
		input.addValue(totalVolume2012 , "Volume of stock 2012", "Volume of stock 2012");
		input.addValue(totalVolume2013 , "Volume of stock 2013", "Volume of stock 2013");
		input.addValue(totalVolume2014 , "Volume of stock 2014", "Volume of stock 2014");
		input.addValue(totalVolume2015 , "Volume of stock 2015", "Volume of stock 2015");
		input.addValue(totalVolume2016 , "Volume of stock 2016", "Volume of stock 2016");
		input.addValue(totalVolume2017 , "Volume of stock 2017", "Volume of stock 2017");
		input.addValue(totalVolume2018 , "Volume of stock 2018", "Volume of stock 2018");
		
		//In hindsight, I wouldn't have this code in the utilities class but it works.
	    JFreeChart chart = ChartFactory.createBarChart("Frequency of Google stocks sold" , "Amount", "Frequency", (CategoryDataset) input, PlotOrientation.VERTICAL, true, true, false);
	    ChartPanel graphPanel = new ChartPanel(chart);
	    graphPanel.setVisible(true);
	    tabbedPane.add("Bar graph", graphPanel);
	}
	/**
	 * This is the method that creates the Pie graph to display
	 * @param data the shared arraylist
	 * @param tabbedPane The target to display the pieChart
	 */
	public static void createAPieGraph(ArrayList<Google> data, JTabbedPane tabbedPane) {
		DefaultPieDataset input = new DefaultPieDataset();
		int lessThnOneMil=0;
		int lessThnFiveMil=0;
		int lessThnTenMil=0;
		int moreThnTenMil = 0;
		
		//This method works by incrementing a counter when ever the determine range returns each ranges value
		for(int i = 0; i < data.size() - 1; i++) {			
			switch(determineRange(data.get(i).getVolume())) {
			case 0 :
				lessThnOneMil++;
				break;
			case 1:
				lessThnFiveMil++;
				break;
			case 2 :
				lessThnTenMil++;
				break;
			case 3 :
				moreThnTenMil++;
				break;
			}
		}
		
		input.setValue("Less Than One Mil", lessThnOneMil);
		input.setValue("Less Than Five Mil", lessThnFiveMil);
		input.setValue("Less Than Ten Mil", lessThnTenMil);
		input.setValue("More than ten Mil", moreThnTenMil);
		
		JFreeChart chart = ChartFactory.createPieChart("Amount of stock sold (range)", input, true, true, Locale.ENGLISH);		
		ChartPanel mypanel = new ChartPanel(chart);
		mypanel.setVisible(true);
		
		tabbedPane.add("Pie Graph", mypanel);
	}
	/**
	 * This method is used to split incoming data into ranges, so it can be displayed in a pie chart
	 * @param long1
	 * @return
	 */
	private static int determineRange(Long long1) {
		int result = 0;
		
		if (long1 < 1000000) {
			result = 0;
		}
		else if (long1 < 5000000) {
			result = 1;
		}
		else if (long1 < 9999999) {
			result = 2;
		}
		else if (long1 > 10000000) {
			result = 3;
		}
		return result;
	}
	
	/**
	 * This method is used to search the data for a user specified value.
	 * It works by taking a string from a combo box to specify which category
	 *  the user is searching in, and then running the appropriate search 
	 *  Algorithm related to the search category
	 * @param data the shared arrayList
	 * @param selection the selected combo box input
	 * @param search the target table to output the data
	 * @param input the text the user entered
	 * @return an int for some basic error checking, this allows the code to display if there is no record found
	 *  rather than run a null point error
	 */
	public static int searchData(ArrayList<Google> data, String selection, DefaultTableModel search, String input) {
		double inputDouble = 0;
		long inputLong = 0;
		
		selection = selection.toUpperCase();
		Selection category = Selection.valueOf(selection);
		
		switch(category) {
		case DATE:						
			createSingleResult(search, data, dateSearch(data, input));
			return dateSearch(data, input);						
		case OPEN:
			inputDouble = Double.parseDouble(input);
			createSingleResult(search,data, openSearch(data,inputDouble));
			return openSearch(data,inputDouble);
		case CLOSE:
			inputDouble = Double.parseDouble(input);
			createSingleResult(search,data, closeSearch(data,inputDouble));
			return closeSearch(data,inputDouble);
		case HIGH:
			inputDouble = Double.parseDouble(input);
			createSingleResult(search,data, highSearch(data,inputDouble));
			return highSearch(data,inputDouble);
		case LOW:
			inputDouble = Double.parseDouble(input);
			createSingleResult(search,data, lowSearch(data,inputDouble));
			return lowSearch(data,inputDouble);
		case ADJCLOSE:
			inputDouble = Double.parseDouble(input);
			createSingleResult(search,data, adjCloseSearch(data,inputDouble));
			return adjCloseSearch(data,inputDouble);
		case VOLUME:
			inputLong = Long.parseLong(input);
			createSingleResult(search,data, volumeSearch(data,inputLong));
			return volumeSearch(data, inputLong);
		}
		return -1;
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int dateSearch(ArrayList<Google> data, String wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getDate().equals(wanted) == true) {
				return i;
			}
		}
		return -1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int openSearch(ArrayList<Google> data, Double wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getOpen().equals(wanted) == true) {
				return i;
			}
		}
		return	-1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int closeSearch(ArrayList<Google> data, Double wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getClose().equals(wanted) == true) {
				return i;
			}
		}
		return	-1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int highSearch(ArrayList<Google> data, Double wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getHigh().equals(wanted) == true) {
				return i;
			}
		}
		return	-1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int lowSearch(ArrayList<Google> data, Double wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getLow().equals(wanted) == true) {
				return i;
			}
		}
		return	-1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int adjCloseSearch(ArrayList<Google> data, Double wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getAdjClose().equals(wanted) == true) {
				return i;
			}
		}
		return	-1;	
	}
	/**
	 * These methods all do a similar task, they search for if two inputs are the same,
	 * and if they are, returns the index position. The only primary difference is the get method accessed.
	 * @param data the shared arrayList 
	 * @param wanted the search value to compare against
	 * @return an int that states the index value the found data is stored at.
	 */
	private static int volumeSearch(ArrayList<Google> data, long wanted) {
		for(int i = 0; i < data.size() - 1; i++) {
			if(data.get(i).getVolume() == wanted) {
				return i;
			}
		}
		return	-1;	
	}

	/**
	 * This is code to draw a single result to the targeted table based on the variables it gets passed
	 * @param search the jtable target that states where to place the information
	 * @param data the shared arraylist
	 * @param index allows for error checking as it functions as an index
	 */
	public static void createSingleResult(DefaultTableModel search,  ArrayList<Google> data, int index) {
		if(index != -1) {
		search.setRowCount(0);		
		Object[] object = new Object[7];
		object[0] = data.get(index).getDate();
		object[1] = data.get(index).getOpen();
		object[2] = data.get(index).getHigh();
		object[3] = data.get(index).getLow();
		object[4] = data.get(index).getClose();
		object[5] = data.get(index).getAdjClose();
		object[6] = data.get(index).getVolume();
		search.addRow(object);
		}
	}
	/**
	 * This method is to find the max value of a selected category
	 * @param data the shared arrayList
	 * @param comboBox the user selected category
	 * @param search the target table to print the result 
	 */
	public static void findMax(ArrayList<Google> data, String comboBox, DefaultTableModel search) {
		comboBox = comboBox.toUpperCase();
		Selection category = Selection.valueOf(comboBox);	
		
		double highestDouble;
		String highestDate;
		long highestLong;
		int storedIndex = -1;
		
		switch(category) {		
		case DATE :
			highestDate = data.get(0).getDate();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getDate().compareTo(highestDate) > 0) {
					highestDate = data.get(i).getDate();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case OPEN :
			highestDouble = data.get(0).getOpen();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getOpen() > highestDouble) {
					highestDouble = data.get(i).getOpen();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case CLOSE :
			highestDouble = data.get(0).getClose();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getClose() > highestDouble) {
					highestDouble = data.get(i).getClose();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case HIGH:
			highestDouble = data.get(0).getHigh();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getHigh() > highestDouble) {
					highestDouble = data.get(i).getHigh();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case LOW:
			highestDouble = data.get(0).getLow();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getLow() > highestDouble) {
					highestDouble = data.get(i).getLow();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case ADJCLOSE:
			highestDouble = data.get(0).getAdjClose();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getAdjClose() > highestDouble) {
					highestDouble = data.get(i).getAdjClose();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case VOLUME:
			highestLong = data.get(0).getVolume();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getVolume() > highestLong) {
					highestDouble = data.get(i).getAdjClose();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
		}
	}
	/**
	 * The code to find the minimum value from a selected value
	 * @param data the shared arrayList
	 * @param comboBox the user selected category
	 * @param search the target table to print to
	 */
	public static void findMini(ArrayList<Google> data, String comboBox, DefaultTableModel search) {
		comboBox = comboBox.toUpperCase();
		Selection category = Selection.valueOf(comboBox);	
		
		double lowestDouble;
		String lowestDate;
		long lowestLong;
		int storedIndex = 0;
		
		switch(category) {		
		case DATE :
			lowestDate = data.get(0).getDate();
			for(int i = 0; i < data.size(); i++) {
				System.out.println(lowestDate);
				if(data.get(i).getDate().compareTo(lowestDate) < 0) {
					lowestDate = data.get(i).getDate();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case OPEN :
			lowestDouble = data.get(0).getOpen();
			for(int i = 0; i < data.size(); i++) {
				System.out.println(lowestDouble);
				if(data.get(i).getOpen() < lowestDouble) {
					lowestDouble = data.get(i).getOpen();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case CLOSE :
			lowestDouble = data.get(0).getClose();
			for(int i = 0; i < data.size(); i++) {
				System.out.println(lowestDouble);
				if(data.get(i).getClose() < lowestDouble) {
					lowestDouble = data.get(i).getClose();
					System.out.println(lowestDouble);
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case HIGH:
			lowestDouble = data.get(0).getHigh();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getHigh() < lowestDouble) {
					lowestDouble = data.get(i).getHigh();
					System.out.println(lowestDouble);
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case LOW:
			lowestDouble = data.get(0).getLow();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getLow() < lowestDouble) {
					lowestDouble = data.get(i).getLow();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case ADJCLOSE:
			lowestDouble = data.get(0).getAdjClose();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getAdjClose() < lowestDouble) {
					lowestDouble = data.get(i).getAdjClose();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
			break;
		case VOLUME:
			lowestLong = data.get(0).getVolume();
			for(int i = 0; i < data.size(); i++) {
				if(data.get(i).getVolume() < lowestLong) {
					lowestDouble = data.get(i).getAdjClose();
					storedIndex = i;
				}
			}
			createSingleResult(search, data, storedIndex);
		}
	}
	/**
	 * This method is used to sort the data into ascending order based on the user selected data
	 * 
	 * @param data the shared arrayList
	 * @param comboBox the selected category
	 */
	public static void sortAscList(ArrayList<Google> data, String comboBox) {
		comboBox = comboBox.toUpperCase();
		Selection category = Selection.valueOf(comboBox);
		
		Google temp;
		
		switch(category) {		
		case DATE :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getDate().compareTo(data.get(j + 1).getDate()) > 0) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case OPEN :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getOpen() > data.get(j + 1).getOpen()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case CLOSE :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getClose() > data.get(j + 1).getClose()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case HIGH:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getHigh() > data.get(j + 1).getHigh()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case LOW:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getLow() > data.get(j + 1).getLow()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case ADJCLOSE:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getAdjClose() > data.get(j + 1).getAdjClose()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case VOLUME:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getVolume() > data.get(j + 1).getVolume()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		}
	}
	/**
	 * This is the method to sort the arrayList into descending order based on the user selected category
	 * @param data the shared arrayList
	 * @param comboBox the user selected category 
	 */
	public static void sortDescList(ArrayList<Google> data, String comboBox) {
		comboBox = comboBox.toUpperCase();
		Selection category = Selection.valueOf(comboBox);
		
		Google temp;
		
		switch(category) {		
		case DATE :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getDate().compareTo(data.get(j + 1).getDate()) < 0) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case OPEN :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getOpen() < data.get(j + 1).getOpen()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case CLOSE :
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getClose() < data.get(j + 1).getClose()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case HIGH:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getHigh() < data.get(j + 1).getHigh()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case LOW:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getLow() < data.get(j + 1).getLow()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case ADJCLOSE:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getAdjClose() < data.get(j + 1).getAdjClose()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		case VOLUME:
			for(int i = 0; i < data.size(); i++) {
				for(int j = 0; j < data.size() - 1 - i; j++) {
					if(data.get(j).getVolume() < data.get(j + 1).getVolume()) {
						temp = data.get(j);
						data.set(j, data.get(j + 1));
						data.set(j+1, temp);
					}
				}
			}
			break;
		}
	}	
}