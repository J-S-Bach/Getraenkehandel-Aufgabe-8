package main;

//import exports.DataExporter;
import inheritance.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		LocationManager manager = new LocationManager();

		List<Location> locations = new ArrayList<>();
		locations.add(manager.getCentral());
		locations.addAll(Arrays.asList(manager.getLocations()));

		//String s = DataExporter.getLocationsDataInJSONFormat(locations);

		System.out.println(locations);
	}

}
