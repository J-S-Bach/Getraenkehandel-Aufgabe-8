//package exports;
//
//import inheritance.DrinkType;
//import inheritance.Location;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
///**
// * Converts the given data into external formats like XML or CSV.
// * @author Cedric Schmitt & J. Sebastian Kirner
// */
//
//public class DataExporter {
//
//    /**
//     * Converts the given locations into a csv formatted string. Header is included per default.
//     *
//     * @param location Location
//     * @return String in csv format
//     */
//    public static String getLocationDataInCSVFormat(Location location) {
//        return getLocationDataInCSVFormat(location, true);
//    }
//
//    /**
//     * Converts the given location into a csv formatted string.
//     *
//     * @param location Location
//     * @return String in CSV format
//     */
//    public static String getLocationDataInCSVFormat(Location location, boolean includeHeader) {
//        Map<DrinkType, Integer> capacity = location.getCapacity();
//        Map<DrinkType, Integer> drinks = location.getDrinks();
//
//        StringBuilder csv = new StringBuilder();
//
//        if (includeHeader) {
//            csv.append("Name;Attribute;\"Flaschen pro Kiste\";\"Anzahl Flaschen\";\"Anzahl Kisten\";\"Lagerkapazität\"")
//                    .append(System.lineSeparator()); //Plattformunabhängiger New-Line Character
//        }
//
//        DrinkType drink;
//        int numBoxes;
//		/*
//			Generates a new line for every type of drink and appends it to the output.
//		 */
//        for (Map.Entry<DrinkType, Integer> entry : drinks.entrySet()) {
//            drink = entry.getKey();
//
//            numBoxes = drink.bottlesToBoxes(entry.getValue());
//
//            if (entry.getValue() % drink.getMaxBottles() > 0) {
//                numBoxes += 1;
//            }
//
//            csv.append(
//                    String.format("\"%s\";\"%s\";%d;%d;%d;%d%n",
//                            drink.getType(),
//                            drink.getAttributes(),
//                            drink.getMaxBottles(),
//                            entry.getValue(),
//                            numBoxes,
//                            capacity.get(drink))
//            );
//        }
//
//        return csv.toString();
//    }
//
//    /**
//     * Converts the given location into a XML formatted string. Header is included per default.
//     *
//     * @param location Location
//     * @return String in XML format
//     */
//    public static String getLocationDataInXMLFormat(Location location) {
//        return getLocationDataInXMLFormat(location, true);
//    }
//
//
//    /**
//     * Converts the given location into a XML formatted string.
//     *
//     * @param location Location
//     * @param includeHeader boolean if the header should be included
//     * @return String in XML format
//     */
//    public static String getLocationDataInXMLFormat(Location location, boolean includeHeader) {
//        Map<DrinkType, Integer> capacity = location.getCapacity();
//        Map<DrinkType, Integer> drinks = location.getDrinks();
//
//        StringBuilder xml = new StringBuilder();
//        if (includeHeader) {
//            xml.append("<?xml version=\"1.0\" ?>");
//            xml.append(System.lineSeparator());
//        }
//
//        xml.append("""
//                <Location name="%s">
//                """.formatted(location.getName()));
//
//        DrinkType drink;
//        int numBoxes;
//
//		/*
//			Generates a new line for every type of drink and appends it to the output.
//		 */
//        for (Map.Entry<DrinkType, Integer> entry : drinks.entrySet()) {
//            drink = entry.getKey();
//
//            numBoxes = drink.bottlesToBoxes(entry.getValue());
//
//            if (entry.getValue() % drink.getMaxBottles() > 0) {
//                numBoxes += 1;
//            }
//
//            xml.append(
//                    String.format("\t<DrinkType type=\"%s\" attributes=\"%s\" bottlesPerBox=\"%d\" numBottles=\"%d\" numBoxes=\"%d\" storageCapacity=\"%d\" />%n",
//                            drink.getType(),
//                            drink.getAttributes(),
//                            drink.getMaxBottles(),
//                            entry.getValue(),
//                            numBoxes,
//                            capacity.get(drink))
//            );
//        }
//        xml.append("</Location>").append(System.lineSeparator());
//
//        return xml.toString();
//    }
//
//
//    /**
//     * Converts the given location into a JSON formatted string.
//     *
//     * @param location Location
//     * @return String in JSON format
//     */
//    public static String getLocationDataInJSONFormat(Location location) {
//        Map<DrinkType, Integer> capacity = location.getCapacity();
//        Map<DrinkType, Integer> drinks = location.getDrinks();
//
//        StringBuilder json = new StringBuilder();
//
//        json.append("""
//                {
//                	"name":"%s",
//                	"data": [
//                """.formatted(location.getName()));
//
//        DrinkType drink;
//        int numBoxes;
//
//        Iterator<Map.Entry<DrinkType, Integer>> it = drinks.entrySet().iterator();
//
//        Map.Entry<DrinkType, Integer> entry;
//
//        /*
//			Generates a new line for every type of drink and appends it to the output.
//		 */
//        while (it.hasNext()) {
//            entry = it.next();
//            drink = entry.getKey();
//
//            numBoxes = drink.bottlesToBoxes(entry.getValue());
//
//            if (entry.getValue() % drink.getMaxBottles() > 0) {
//                numBoxes += 1;
//            }
//
//            if (it.hasNext()) {
//                json.append("""
//                        		{
//                        			"type": "%s",
//                        			"attributes": "%s",
//                        			"bottlesPerBox": %d,
//                        			"numBottles": %d,
//                        			"numBoxes": %d,
//                        			"storageCapacity": %d
//                        		},
//                        """.formatted(drink.getType(),
//                        drink.getAttributes(),
//                        drink.getMaxBottles(),
//                        entry.getValue(),
//                        numBoxes,
//                        capacity.get(drink)));
//            } else {
//                json.append("""
//                        		{
//                        			"type": "%s",
//                        			"attributes": "%s",
//                        			"bottlesPerBox": %d,
//                        			"numBottles": %d,
//                        			"numBoxes": %d,
//                        			"storageCapacity": %d
//                        		}
//                        """.formatted(drink.getType(),
//                        drink.getAttributes(),
//                        drink.getMaxBottles(),
//                        entry.getValue(),
//                        numBoxes,
//                        capacity.get(drink)));
//            }
//        }
//
//        json.append("\t]").append(System.lineSeparator());
//        json.append("}");
//
//        return json.toString();
//    }
//
//    /**
//     * Converts the given locations into a csv formatted string. Header is included per default.
//     *
//     * @param locations List
//     * @return String in csv format
//     */
//    public static String getLocationsDataInCSVFormat(List<Location> locations) {
//        return getLocationsDataInCSVFormat(locations, true);
//    }
//
//    /**
//     * Converts the given locations into a csv formatted string.
//     *
//     * @param locations     List
//     * @param includeHeader boolean
//     * @return String in csv format
//     */
//    public static String getLocationsDataInCSVFormat(List<Location> locations, boolean includeHeader) {
//        StringBuilder csv = new StringBuilder();
//
//        if (includeHeader) {
//            csv.append("Standort;\"Getränk Name\";Attribute;\"Flaschen pro Kiste\";\"Anzahl Flaschen\";\"Anzahl Kisten\";\"Lagerkapazität\"")
//                    .append(System.lineSeparator()); //Plattformunabhängiger New-Line Character
//        }
//
//        String temp;
//
//        /*
//			Generates a new line for every location and appends it to the output.
//		 */
//        for (Location l : locations) {
//            temp = getLocationDataInCSVFormat(l, false);
//
//            for (String line : temp.split(System.lineSeparator())) {
//                if (line.equals(""))
//                    break;
//
//                csv.append(l.getName())
//                        .append(";")
//                        .append(line)
//                        .append(System.lineSeparator());
//            }
//        }
//
//        return csv.toString();
//    }
//
//    /**
//     * Converts the given locations into a XML formatted string. Header is included per default.
//     *
//     * @param locations List
//     * @return String in XML format
//     */
//    public static String getLocationsDataInXMLFormat(List<Location> locations) {
//        return getLocationsDataInXMLFormat(locations, true);
//    }
//
//    /**
//     * Converts the given locations into a XML formatted string.
//     *
//     * @param locations     List
//     * @param includeHeader boolean
//     * @return String in XML format
//     */
//    public static String getLocationsDataInXMLFormat(List<Location> locations, boolean includeHeader) {
//        StringBuilder xml = new StringBuilder();
//
//        if (includeHeader) {
//            xml.append("<?xml version=\"1.0\" ?>");
//            xml.append(System.lineSeparator());
//        }
//
//        xml.append("<Locations>").append(System.lineSeparator());
//
//        /*
//			Generates a new line for every location and appends it to the output.
//		 */
//        for (Location l : locations) {
//            xml.append(getLocationDataInXMLFormat(l, false));
//        }
//
//        xml.append("</Locations>");
//
//        return xml.toString();
//    }
//
//    /**
//     * Converts the given locations into a JSON formatted string.
//     *
//     * @param locations List
//     * @return String in JSON format
//     */
//    public static String getLocationsDataInJSONFormat(List<Location> locations) {
//        StringBuilder json = new StringBuilder();
//
//        json.append("[").append(System.lineSeparator());
//
//        Iterator<Location> it = locations.listIterator();
//
//        Location l;
//        /*
//			Generates a new line for every location and appends it to the output.
//		 */
//        while (it.hasNext()) {
//            l = it.next();
//            json.append(getLocationDataInJSONFormat(l));
//            if (it.hasNext()) {
//                json.append(",").append(System.lineSeparator());
//            }
//        }
//
//        json.append(System.lineSeparator()).append("]");
//
//        return json.toString();
//    }
//}
//
