package exports;

import inheritance.DrinkType;
import inheritance.Location;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DataExporter {

	public static String getLocationDataInCSVFormat(Location location)
	{
		return getLocationDataInCSVFormat(location, true);
	}

	/**
	 * Converts the given location into a csv formatted string.
	 * @param location Location
	 * @return String in CSV format
	 */
	public static String getLocationDataInCSVFormat(Location location, boolean includeHeader) {
		Map<DrinkType, Integer> capacity = location.getCapacity();
		Map<DrinkType, Integer> drinks = location.getDrinks();

		StringBuilder csv = new StringBuilder();

		if (includeHeader) {
			csv.append("Name;Attribute;\"Flaschen pro Kiste\";\"Anzahl Flaschen\";\"Anzahl Kisten\";\"Lagerkapazität\"")
					.append(System.lineSeparator()); //Plattformunabhängiger New-Line Character
		}

		DrinkType drink;
		int numBoxes;

		for (Map.Entry<DrinkType, Integer> entry : drinks.entrySet()) {
			drink = entry.getKey();

			numBoxes = drink.bottlesToBoxes(entry.getValue());

			if (entry.getValue() % drink.getMaxBottles() > 0) {
				numBoxes += 1;
			}

			csv.append(
					String.format("\"%s\";\"%s\";%d;%d;%d;%d%n",
							drink.getType(),
							drink.getAttributes(),
							drink.getMaxBottles(),
							entry.getValue(),
							numBoxes,
							capacity.get(drink))
			);
		}

		return csv.toString();
	}

	public static String getLocationDataInXMLFormat(Location location)
	{
		return getLocationDataInXMLFormat(location, true);
	}


	/**
	 * Converts the given location into a csv formatted string.
	 * @param location Location
	 * @return String in CSV format
	 */
	public static String getLocationDataInXMLFormat(Location location, boolean includeHeader) {
		Map<DrinkType, Integer> capacity = location.getCapacity();
		Map<DrinkType, Integer> drinks = location.getDrinks();

		StringBuilder xml = new StringBuilder();
		if (includeHeader) {
			xml.append("<?xml version=\"1.0\" ?>");
			xml.append(System.lineSeparator());
		}

		xml.append("""
  			<Location name="%s">
  			""".formatted(location.getName()));

		DrinkType drink;
		int numBoxes;


		for (Map.Entry<DrinkType, Integer> entry : drinks.entrySet()) {
			drink = entry.getKey();

			numBoxes = drink.bottlesToBoxes(entry.getValue());

			if (entry.getValue() % drink.getMaxBottles() > 0) {
				numBoxes += 1;
			}

			xml.append(
					String.format("\t<DrinkType type=\"%s\" attributes=\"%s\" bottlesPerBox=\"%d\" numBottles=\"%d\" numBoxes=\"%d\" storageCapacity=\"%d\" />%n",
							drink.getType(),
							drink.getAttributes(),
							drink.getMaxBottles(),
							entry.getValue(),
							numBoxes,
							capacity.get(drink))
			);
		}
		xml.append("</Location>").append(System.lineSeparator());

		return xml.toString();
	}


	/**
	 * Converts the given location into a csv formatted string.
	 * @param location Location
	 * @return String in CSV format
	 */
	public static String getLocationDataInJSONFormat(Location location) {
		Map<DrinkType, Integer> capacity = location.getCapacity();
		Map<DrinkType, Integer> drinks = location.getDrinks();

		StringBuilder json = new StringBuilder();

		json.append("""
			{
				"name":"%s",
				"data": [
			""".formatted(location.getName()));

		DrinkType drink;
		int numBoxes;

		Iterator<Map.Entry<DrinkType, Integer>> it = drinks.entrySet().iterator();

		Map.Entry<DrinkType, Integer> entry;

		while(it.hasNext())
		{
			entry = it.next();
			drink = entry.getKey();

			numBoxes = drink.bottlesToBoxes(entry.getValue());

			if (entry.getValue() % drink.getMaxBottles() > 0) {
				numBoxes += 1;
			}

			if (it.hasNext()) {
				json.append("""
							{
								"type": "%s",
								"attributes": "%s",
								"bottlesPerBox": %d,
								"numBottles": %d,
								"numBoxes": %d,
								"storageCapacity": %d
							},
					""".formatted(drink.getType(),
						drink.getAttributes(),
						drink.getMaxBottles(),
						entry.getValue(),
						numBoxes,
						capacity.get(drink)));
			} else {
				json.append("""
							{
								"type": "%s",
								"attributes": "%s",
								"bottlesPerBox": %d,
								"numBottles": %d,
								"numBoxes": %d,
								"storageCapacity": %d
							}
					""".formatted(drink.getType(),
						drink.getAttributes(),
						drink.getMaxBottles(),
						entry.getValue(),
						numBoxes,
						capacity.get(drink)));
			}
		}

		json.append("\t]").append(System.lineSeparator());
		json.append("}");

		return json.toString();
	}


	public static String getLocationsDataInCSVFormat(List<Location> locations) {
		return getLocationsDataInCSVFormat(locations, true);
	}

	public static String getLocationsDataInCSVFormat(List<Location> locations, boolean includeHeader) {
		StringBuilder csv = new StringBuilder();

		if (includeHeader) {
			csv.append("Standort;\"Getränk Name\";Attribute;\"Flaschen pro Kiste\";\"Anzahl Flaschen\";\"Anzahl Kisten\";\"Lagerkapazität\"")
					.append(System.lineSeparator()); //Plattformunabhängiger New-Line Character
		}

		String temp;
		for (Location l : locations) {
			temp = getLocationDataInCSVFormat(l, false);

			for (String line : temp.split(System.lineSeparator())) {
				if (line.equals(""))
					break;

				csv.append(l.getName())
						.append(";")
						.append(line)
						.append(System.lineSeparator());
			}
		}

		return csv.toString();
	}


	public static String getLocationsDataInXMLFormat(List<Location> locations) {
		return getLocationsDataInXMLFormat(locations, true);
	}

	public static String getLocationsDataInXMLFormat(List<Location> locations, boolean includeHeader) {
		StringBuilder xml = new StringBuilder();

		if (includeHeader) {
			xml.append("<?xml version=\"1.0\" ?>");
			xml.append(System.lineSeparator());
		}

		xml.append("<Locations>").append(System.lineSeparator());

		for (Location l : locations) {
			xml.append(getLocationDataInXMLFormat(l, false));
		}

		xml.append("</Locations>");

		return xml.toString();
	}


	public static String getLocationsDataInJSONFormat(List<Location> locations) {
		StringBuilder json = new StringBuilder();

		json.append("[").append(System.lineSeparator());

		Iterator<Location> it = locations.listIterator();

		Location l;
		while(it.hasNext()) {
			l = it.next();
			json.append(getLocationDataInJSONFormat(l));
			if(it.hasNext()) {
				json.append(",").append(System.lineSeparator());
			}
		}

		json.append(System.lineSeparator()).append("]");

		return json.toString();
	}
}
