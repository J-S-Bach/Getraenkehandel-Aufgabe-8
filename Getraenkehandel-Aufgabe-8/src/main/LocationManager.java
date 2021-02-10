package main;

import inheritance.DrinkType;
import inheritance.Location;
import inheritance.CentralStorage;

public class LocationManager {
	
	private Location[] locations = new Location[2];
	private Location central = null;
	
	public LocationManager() {
		
	}
	
	private void setupLocations() {
		CentralStorage zentral = new CentralStorage("Zentrallager");
		
		Location standort1 = new Location("Standort 1");
		/*standort1.setCapacity(drinkType, 100);
		standort1.setCapacity(drinkType, 200);
		standort1.setCapacity(drinkType, 100);
		standort1.setCapacity(drinkType, 200);
		standort1.setCapacity(drinkType, 150);
		standort1.setCapacity(drinkType, 150);
		*/
		Location standort2 = new Location("Standort 2");
		/*standort2.setCapacity(drinkType, 50);
		standort2.setCapacity(drinkType, 100);
		standort2.setCapacity(drinkType, 50);
		standort2.setCapacity(drinkType, 200);
		standort2.setCapacity(drinkType, 100);
		standort2.setCapacity(drinkType, 150);
		*/
		central = zentral;
		locations[0] = standort1;
		locations[1] = standort2;
	}
	
}
