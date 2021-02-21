package inheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		Integer a = capacity.get(drinkType);
		return a;
	}

	public DrinkType[] getDrinkTypes() {
		return drinks.keySet().toArray(new DrinkType[drinks.keySet().size()]);
	}

	public int getDrinkAmount(DrinkType drinkType) {
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

	public void fillFromLocation(DrinkType drinkType, Location from) throws Exception {
		int missingAmount = drinkType.movableBottles(this.getMissing(drinkType));
		if (from.getDrinkAmount(drinkType) < missingAmount) {
			missingAmount = from.getDrinkAmount(drinkType);	//set amount to max of "from"
			throw new Exception("Not enough " + drinkType.type + " -> Filling with max!");
		}
		this.addDrink(drinkType, missingAmount);
		from.removeDrink(drinkType, missingAmount);
	}

	public void fillEveryDrinkFromLocation(Location from) throws Exception {
		for (DrinkType dt : this.getDrinkTypes()) {
			this.fillFromLocation(dt, from);
		}
	}

	public String toString() {
		StringBuilder out = new StringBuilder();
		for (DrinkType dt : this.getDrinkTypes()) {
			out.append(dt.getType() + ": " + this.getDrinkAmount(dt) + "/" + this.getDrinkCapacity(dt) + " Bottles ("
					+ dt.bottlesToBoxes(this.getDrinkAmount(dt)) + "/" + dt.bottlesToBoxes(this.getDrinkCapacity(dt))
					+ " Boxes)\n");
		}
		return out.toString();
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
