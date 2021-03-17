package gui;

import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JTable;

import main.LocationManager;
import inheritance.*;

/**
 * The class GUIHelperMethods stores helping methods to use in GUI
 * 
 * @author Jan Bauer
 */
public class GUIHelperMethods {

	/**
	 * Stores a list of locations given from constructor
	 */
	private final List<Location> locations;

	/**
	 * Constructor of GUIHelperMethods
	 * 
	 * @param allLocations is a List from Location
	 */
	public GUIHelperMethods(List<Location> allLocations) {
		locations = allLocations;
	}

	/**
	 * The method generate an String[] with all names of Locations stored in it
	 * 
	 * @return returns a String[] with all Names of Location
	 */
	protected String[] getAllLocationNames() {

		String[] locationNames = new String[locations.size()];

		for (int i = 0; i < locations.size(); i++) {
			locationNames[i] = locations.get(i).getName();
		}
		return locationNames;
	}

	/**
	 * The method generate a String[] with all products from a specific location
	 * 
	 * @param indexOfLocation Index of location
	 * @return Returns a String[] with all product from a specific location
	 */
	protected String[] getAllProductsFromLocation(Object indexOfLocation) {

		int index = (Integer) indexOfLocation;

		DrinkType[] allDrinkTypes = locations.get(index).getDrinkTypes();
		String[] drinkNames = new String[allDrinkTypes.length];

		for (int i = 0; i < allDrinkTypes.length; i++) {
			drinkNames[i] = allDrinkTypes[i].getType();
		}

		return drinkNames;
	}

	/**
	 * This method compares a string to all DrinkTypes names
	 * 
	 * @param indexOfLocation       Index of location
	 * @param productName Name of a product
	 * @return Returns the matching DrinkType
	 */
	protected DrinkType stingToDrinkType(Object indexOfLocation, Object productName) {

		String selectedProduct = (String) productName;
		int index = (Integer) indexOfLocation;

		DrinkType[] allDrinkTypes = locations.get(index).getDrinkTypes();

		for (int i = 0; i < allDrinkTypes.length; i++) {
			if (selectedProduct.equals(allDrinkTypes[i].getType())) {
				return allDrinkTypes[i];
			}
		}

		return null;
	}

	/**
	 * This method generate a ImageIcon from a string path
	 * 
	 * @param stringPath Takes a string with a path inside "some/path/image.png"
	 * @return Returns a ImageIcon
	 */
	public ImageIcon generateImageIcon(String stringPath) {
		URL iconURL = getClass().getResource(stringPath);
		ImageIcon icon = new ImageIcon(iconURL);

		return icon;
	}

	/**
	 * The method generate the range of products from a location
	 * 
	 * @param indexOfLocation Index of location
	 * @return Returns a Object[][] filled with the hole range of products
	 */
	public Object[][] getSortimentOfLocation(Object indexOfLocation) {

		int index = (Integer) indexOfLocation;
		DrinkType[] allDrinkTypes = locations.get(index).getDrinkTypes();
		Object[][] sortiment = new String[allDrinkTypes.length][5];

		for (int rows = 0; rows < sortiment.length; rows++) {
			for (int columns = 0; columns <= 0; columns++) {
				sortiment[rows][columns] = allDrinkTypes[rows].getType();
				sortiment[rows][columns + 1] = locations.get(index).getDrinkAmount(allDrinkTypes[rows]) + "";
				sortiment[rows][columns + 2] = locations.get(index).getDrinkCapacity(allDrinkTypes[rows]) + "";
				sortiment[rows][columns + 3] = allDrinkTypes[rows].bottlesToBoxes(locations.get(index).getDrinkAmount(allDrinkTypes[rows]))
						+ "";
				sortiment[rows][columns + 4] = allDrinkTypes[rows]
						.bottlesToBoxes(locations.get(index).getDrinkCapacity(allDrinkTypes[rows])) + "";
			}
		}

		return sortiment;
	}

	/**
	 * This method generate the range of products and the product attributes from a location
	 * @param indexOfLocation Index of location
	 * @return Returns a Object[][] filled with the hole range of products and its attributes
	 */
	public Object[][] getSortimentAndAttributesOfLocation(Object indexOfLocation) {

		DrinkType[] allDrinkTypes = locations.get((Integer) (Integer) indexOfLocation).getDrinkTypes();
		Object[][] sortimentWithAttributes = new Object[allDrinkTypes.length][6];

		for (int rows = 0; rows < sortimentWithAttributes.length; rows++) {
			for (int columns = 0; columns <= 0; columns++) {
				sortimentWithAttributes[rows][columns] = allDrinkTypes[rows].getType();
				sortimentWithAttributes[rows][columns + 1] = locations.get((Integer) indexOfLocation).getDrinkAmount(allDrinkTypes[rows])
						+ "";
				sortimentWithAttributes[rows][columns + 2] = locations.get((Integer) indexOfLocation).getDrinkCapacity(allDrinkTypes[rows])
						+ "";
				sortimentWithAttributes[rows][columns + 3] = allDrinkTypes[rows]
						.bottlesToBoxes(locations.get((Integer) indexOfLocation).getDrinkAmount(allDrinkTypes[rows])) + "";
				sortimentWithAttributes[rows][columns + 4] = allDrinkTypes[rows]
						.bottlesToBoxes(locations.get((Integer) indexOfLocation).getDrinkCapacity(allDrinkTypes[rows])) + "";
				sortimentWithAttributes[rows][columns + 5] = allDrinkTypes[rows].getAttributes();
			}
		}

		return sortimentWithAttributes;
	}

	/**
	 * This method merges two object[][]
	 * @param firstObject First object[][]
	 * @param secondObject Second object[][]
	 * @return Returns a merged object[][]
	 */
	public Object[][] createMergedObject(Object[][] firstObject, Object[][] secondObject) {

		Object[][] mergedObject = Arrays.copyOf(firstObject, firstObject.length + secondObject.length);
		System.arraycopy(secondObject, 0, mergedObject, firstObject.length, secondObject.length);

		return mergedObject;
	}

	/**
	 * Renders the table and give colors to the rows
	 * 
	 * @param headerColumn     The column name in which the method will search
	 * @param destinationTable the table in which the method render
	 */
	public void renderColor(Object[] headerColumn, JTable destinationTable) {

		ColorRenderer cr = new ColorRenderer(headerColumn);

		for (int i = 0; i < headerColumn.length; i++) {
			destinationTable.getColumn(destinationTable.getColumnName(i)).setCellRenderer(cr);
		}
	}

}
