package main;

//import exports.DataExporter;
import inheritance.Location;
import inheritance.OrangeJuice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LocationManager manager = new LocationManager();

		List<Location> locations = new ArrayList<>();
		locations.add(manager.getCentral());
		locations.addAll(Arrays.asList(manager.getLocations()));

		try {
			locations.get(2).addDrink(new OrangeJuice(20), 200);
			locations.get(2).removeDrink(locations.get(2).getDrinkTypes()[0], 50);
			locations.get(2).moveDrinks(locations.get(1), locations.get(2).getDrinkTypes()[0], 2);
		} catch (Exception e) {}

		// String s = DataExporter.getLocationsDataInJSONFormat(locations);
		// locations.get(2).getDrinkTypes()[0]

		System.out.println(locations);

	}

}
