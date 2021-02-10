package inheritance;

import java.util.HashMap;
import java.util.Map;

public class Location {
	private String name = "";
	private Map<DrinkType, Integer> capacity = new HashMap<>();
	private Map<DrinkType, Integer> drinks = new HashMap<>();
	
	public Location() {
		
	}
}
