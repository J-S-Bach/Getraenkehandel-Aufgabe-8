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

	public void setCapacity(DrinkType drinkType, Integer amount) {
		capacity.put(drinkType, amount);
	}

	public int addDrink(DrinkType drinkType, Integer amount) {
		Integer currentAmount = drinks.get(drinkType);
		if (currentAmount + amount > capacity.get(drinkType)) {
			drinks.put(drinkType, capacity.get(drinkType));
			return (amount + currentAmount) - capacity.get(drinkType);
		}
		drinks.put(drinkType, drinks.get(drinkType) + amount);
		return 0;
	}

	public int removeDrink(DrinkType drinkType, Integer amount) {
		Integer currentAmount = drinks.get(drinkType);
		if (currentAmount < amount) {
			drinks.put(drinkType, 0);
			return amount - currentAmount;
		}
		drinks.put(drinkType, currentAmount - amount);
		return 0;
	}

}
