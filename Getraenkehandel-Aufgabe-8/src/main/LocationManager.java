package main;

import inheritance.*;

public class LocationManager {

	private Location[] locations = new Location[2];
	private CentralStorage central = null;

	public LocationManager() {
		setupLocations();
	}

	private void setupLocations() {
		CentralStorage zentral = new CentralStorage("Zentrallager");

		Location standort1 = new Location("Standort 1");
		standort1.setCapacity(new WaterNonSparkling(BottleType.GLAS), 100);
		standort1.setCapacity(new WaterSparkling(BottleType.PLASTIC), 200);
		standort1.setCapacity(new AppleJuice(30), 100);
		standort1.setCapacity(new OrangeJuice(40), 200);
		standort1.setCapacity(new Lemonade(35), 150);
		standort1.setCapacity(new Beer(7), 150);

		Location standort2 = new Location("Standort 2");
		standort2.setCapacity(new WaterNonSparkling(BottleType.GLAS), 50);
		standort2.setCapacity(new WaterSparkling(BottleType.PLASTIC), 100);
		standort2.setCapacity(new AppleJuice(30), 50);
		standort2.setCapacity(new OrangeJuice(40), 200);
		standort2.setCapacity(new Lemonade(35), 100);
		standort2.setCapacity(new Beer(7), 150);

		central = zentral;
		// System.out.println(central.toString());
		locations[0] = standort1;
		locations[1] = standort2;

	}

	public Location[] getLocations() {
		return locations;
	}

	public Location getCentral() {
		return central;
	}

	public void fillLocations(Location[] locations) throws Exception {
		for (Location l : locations) {
			for (DrinkType dt : l.getDrinkTypes()) {
				l.fillFromLocation(dt, this.central);
			}
		}
	}

	public void autoFill() {
		for (Location l : locations) {
			for (DrinkType dt : l.getDrinkTypes()) {
				if (l.getMissing(dt) > central.getDrinkAmount(dt)) {
					central.addDrink(dt, central.getMissing(dt));
				}
				try {
					l.fillFromLocation(dt, central);
				} catch (Exception e) {}
			}
		}
	}
}
