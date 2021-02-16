package main;

import inheritance.*;

public class LocationManager {
	
	private Location[] locations = new Location[2];
	private Location central = null;
	
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
		System.out.println(standort2.toString());
		standort2.fillFromCentral(standort2.getDrinkTypes()[0], zentral);
		System.out.println(standort2.toString());
		
		central = zentral;
		//System.out.println(central.toString());
		locations[0] = standort1;
		locations[1] = standort2;
	}
	
}
