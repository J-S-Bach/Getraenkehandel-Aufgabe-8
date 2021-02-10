package inheritance;

import java.util.HashMap;
import java.util.Map;

public class Location {
	protected String name = "";
	protected Map<DrinkType, Integer> capacity = new HashMap<>();
	protected Map<DrinkType, Integer> drinks = new HashMap<>();
	
	public Location(String name) {
		this.name = name;
	}
	
	public void setCapacity(DrinkType drinkType, int amount) {
		capacity.put(drinkType, new Integer(amount));
	}
	
}
