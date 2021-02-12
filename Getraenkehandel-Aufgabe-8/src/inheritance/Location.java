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
			return amount - currentAmount;	//returns what is too much
		}
		drinks.put(drinkType, currentAmount - amount);
		return 0;
	}
	
	public int getMissing(DrinkType drinkType) {
		return this.capacity.get(drinkType) - this.drinks.get(drinkType);
	}
	
	public boolean moveDrinks(Location loc, DrinkType drinkType, int boxes) {
		if(drinkType.movableBottles(this.drinks.get(drinkType)) < drinkType.boxesToBottles(boxes))
			return false;	//not enough bottles in storage
		this.addDrink(drinkType, loc.addDrink(drinkType, drinkType.movableBottles(drinkType.boxesToBottles(boxes))));
		return true;
	}
	
	public void fillFromCentral(DrinkType drinkType, CentralStorage central) {
		this.addDrink(drinkType, drinkType.movableBottles(this.getMissing(drinkType)) - central.removeDrink(drinkType, drinkType.movableBottles(this.getMissing(drinkType))));
	}
	
	public String toString() {
		String out = this.name+":\n";
		for (Map.Entry<DrinkType, Integer> entry : this.drinks.entrySet()) {
		    out+=entry.getKey().getType()+" Bottles: "+entry.getValue()+" / "+this.capacity.get(entry.getKey())+" (Boxes: "+entry.getKey().bottlesToBoxes(entry.getValue())+" / "+entry.getKey().bottlesToBoxes(this.capacity.get(entry.getKey()))+")\n";
		}
		return out;
	}

	public String getName() {
		return name;
	}

	public Map<DrinkType, Integer> getCapacity() {
		return capacity;
	}

	public Map<DrinkType, Integer> getDrinks() {
		return drinks;
	}
}
