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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCapacity(DrinkType drinkType, int amount) {
		capacity.put(drinkType, amount);
		this.drinks.put(drinkType, 0);
	}

	public int getDrinkCapacity(DrinkType drinkType) {
		return capacity.get(drinkType);
	}
	
	public DrinkType[] getDrinkTypes() {
		return drinks.keySet().toArray(new DrinkType[drinks.keySet().size()]);
	}

	public int getDrinkAmount(DrinkType drinkType) {
		System.err.println(drinks.get(drinkType));
		return drinks.get(drinkType);
	}

	public boolean addDrink(DrinkType drinkType, int amount) {
		if (this.getDrinkCapacity(drinkType) < this.getDrinkAmount(drinkType) + amount)
			return false; // not enough capacity
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) + amount);
		return true;
	}

	public boolean removeDrink(DrinkType drinkType, int amount) {
		if (this.getDrinkAmount(drinkType) < amount)
			return false; // not enough to remove
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) - amount);
		return true;
	}

	public int getMissing(DrinkType drinkType) {
		return this.getDrinkCapacity(drinkType) - this.getDrinkAmount(drinkType);
	}

	public boolean moveDrinks(Location to, DrinkType drinkType, int boxes) {
		int bottles = drinkType.boxesToBottles(boxes);
		if (this.getDrinkAmount(drinkType) < bottles || to.getMissing(drinkType) < bottles)
			return false; // When not enough drinks in origin or not enough space in destiny
		this.removeDrink(drinkType, bottles);
		to.addDrink(drinkType, bottles);
		return true;
	}

	public boolean fillFromCentral(DrinkType drinkType, CentralStorage central) {
		int missingAmount = drinkType.movableBottles(this.getMissing(drinkType));
		System.err.println(central.getDrinkAmount(drinkType));
		if (central.getDrinkAmount(drinkType) < missingAmount)
			return false;
		this.addDrink(drinkType, missingAmount);
		central.removeDrink(drinkType, missingAmount);
		return true;
	}

	public String toString() {
		String out = this.name + ":\n";
		for (Map.Entry<DrinkType, Integer> entry : this.drinks.entrySet()) {
			out += entry.getKey().getType() + " Bottles: " + entry.getValue() + " / "
					+ this.capacity.get(entry.getKey()) + " (Boxes: " + entry.getKey().bottlesToBoxes(entry.getValue())
					+ " / " + entry.getKey().bottlesToBoxes(this.capacity.get(entry.getKey())) + ")\n";
		}
		return out;
	}

	/*
	 * public int addDrinkAuto(DrinkType drinkType, Integer amount) { Integer
	 * currentAmount = drinks.get(drinkType); if (currentAmount + amount >
	 * capacity.get(drinkType)) { drinks.put(drinkType, capacity.get(drinkType));
	 * return (amount + currentAmount) - capacity.get(drinkType); }
	 * drinks.put(drinkType, drinks.get(drinkType) + amount); return 0; }
	 * 
	 * public int removeDrinkAuto(DrinkType drinkType, Integer amount) { Integer
	 * currentAmount = drinks.get(drinkType); if (currentAmount < amount) {
	 * drinks.put(drinkType, 0); return amount - currentAmount; // returns what is
	 * too much } drinks.put(drinkType, currentAmount - amount); return 0; }
	 */
}
