package main;

import java.util.Arrays;

import inheritance.*;

/**
 * Manages all locations
 * 
 * @author Simon Hoim
 * @author Felix Köhler
 */
public class LocationManager {

	private Location[] locations = new Location[2];
	private CentralStorage central = null;

	/**
	 * creates LocationManager object
	 */
	public LocationManager() {
		setupLocations();
	}

	/**
	 * Initializes all locations and assigns drinktype capacities to them
	 */
	private void setupLocations() {
		CentralStorage zentral = new CentralStorage("Zentrallager");

		Location standort1 = new Location("Standort 1");
		Location standort2 = new Location("Standort 2");
		try {
			standort1.setCapacity(new WaterNonSparkling(BottleType.GLAS), 100);
			standort1.setCapacity(new WaterSparkling(BottleType.PLASTIC), 200);
			standort1.setCapacity(new AppleJuice(30), 100);
			standort1.setCapacity(new OrangeJuice(40), 200);
			standort1.setCapacity(new Lemonade(35), 150);
			standort1.setCapacity(new Beer(7), 150);

			standort2.setCapacity(new WaterNonSparkling(BottleType.GLAS), 50);
			standort2.setCapacity(new WaterSparkling(BottleType.PLASTIC), 100);
			standort2.setCapacity(new AppleJuice(30), 50);
			standort2.setCapacity(new OrangeJuice(40), 200);
			standort2.setCapacity(new Lemonade(35), 100);
			standort2.setCapacity(new Beer(7), 150);
		} catch (Exception e) {
		}
		central = zentral;
		// System.out.println(central.toString());
		locations[0] = standort1;
		locations[1] = standort2;
	}

	/**
	 * Returns all location
	 * 
	 * @return locations
	 */
	public Location[] getLocations() {
		return locations;
	}

	/**
	 * Returns central storage
	 * 
	 * @return central
	 */
	public Location getCentral() {
		return central;
	}

	/**
	 * Fills every location from central storage
	 * 
	 * @param locations Locations to fill
	 * @throws Exception Error if negative value
	 */
	public void fillLocations(Location[] locations) throws Exception {
		for (Location l : locations) {
			for (DrinkType dt : l.getDrinkTypes()) {
				l.fillFromLocation(dt, this.central);
			}
		}
	}

	/**
	 * Fills every location from central storage and fills drinktype in central if
	 * one it is short or empty
	 */
	public void autoFill() {
		for (Location l : locations) {
			for (DrinkType dt : l.getDrinkTypes()) {
				if (l.getMissing(dt) > central.getDrinkAmount(dt)) {
					try {
						central.addDrink(dt, central.getMissing(dt));
					} catch (Exception e) {}
				}
				try {
					l.fillFromLocation(dt, central);
				} catch (Exception e) {
				}
			}
		}
	}
}
