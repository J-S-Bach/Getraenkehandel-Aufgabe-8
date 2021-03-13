package inheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Location class
 * @author Simon Hoim
 * @author Felix Köhler
 */
public class Location {
	protected String name = "";
	protected Map<DrinkType, Integer> capacity = new HashMap<>();
	protected Map<DrinkType, Integer> drinks = new HashMap<>();
	
	/**
	 * Creates location object
	 * @param name
	 */
	public Location(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this location
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of this location
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity for this location
	 * @param drinkType
	 * @param amount
	 * @throws Exception 
	 */
	public void setCapacity(DrinkType drinkType, int amount) throws Exception {
		if(amount < 0)
			throw new Exception("Negative value not valid!");
		capacity.put(drinkType, amount);
		this.drinks.put(drinkType, 0);
	}

	/**
	 * Returns the capacity of this location
	 * @param drinkType
	 * @return capacity
	 */
	public int getDrinkCapacity(DrinkType drinkType) {
		Integer a = capacity.get(drinkType);
		return a;
	}

	/**
	 * Returns all drinktypes that can be stored in this location
	 * @return drinktypes
	 */
	public DrinkType[] getDrinkTypes() {
		return drinks.keySet().toArray(new DrinkType[drinks.keySet().size()]);
	}

	/**
	 * Returns amount of drinks stored in this location
	 * @param drinkType
	 * @return amount
	 */
	public int getDrinkAmount(DrinkType drinkType) {
		return drinks.get(drinkType);
	}

	/**
	 * Adds drink to the storage of this location
	 * @param drinkType
	 * @param amount
	 * @return success
	 * @throws Exception 
	 */
	public boolean addDrink(DrinkType drinkType, int amount) throws Exception {
		if(amount < 0)
			throw new Exception("Negative value not valid!");
		if (this.getDrinkCapacity(drinkType) < this.getDrinkAmount(drinkType) + amount)
			return false; // not enough capacity
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) + amount);
		return true;
	}

	/**
	 * Removes drink from the storage of this location
	 * @param drinkType
	 * @param amount
	 * @return success
	 * @throws Exception 
	 */
	public boolean removeDrink(DrinkType drinkType, int amount) throws Exception {
		if(amount < 0)
			throw new Exception("Negative value not valid!");
		if (this.getDrinkAmount(drinkType) < amount)
			return false; // not enough to remove
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) - amount);
		return true;
	}

	/**
	 * Returns the amount of bottles that are missing for this location to be full of the certain drinktype
	 * @param drinkType
	 * @return amount
	 */
	public int getMissing(DrinkType drinkType) {
		return this.getDrinkCapacity(drinkType) - this.getDrinkAmount(drinkType);
	}

	/**
	 * Moves drinks from this locations 
	 * @param to destination
	 * @param drinkType
	 * @param boxes
	 * @return success
	 * @throws Exception 
	 */
	public boolean moveDrinks(Location to, DrinkType drinkType, int boxes) throws Exception {
		if(boxes <0)
			throw new Exception("Negative value not valid!");
		int bottles = drinkType.boxesToBottles(boxes);
		if (this.getDrinkAmount(drinkType) < bottles || to.getMissing(drinkType) < bottles)
			return false; // When not enough drinks in origin or not enough space in destiny
		this.removeDrink(drinkType, bottles);
		to.addDrink(drinkType, bottles);
		return true;
	}

	/**
	 * Fills this location from a certain location with specified drinktype
	 * @param drinkType
	 * @param from source
	 * @throws Exception
	 */
	public void fillFromLocation(DrinkType drinkType, Location from) throws Exception {
		int missingAmount = drinkType.movableBottles(this.getMissing(drinkType));
		if (from.getDrinkAmount(drinkType) < missingAmount) {
			missingAmount = from.getDrinkAmount(drinkType);	//set amount to max of "from"
			throw new Exception("Not enough " + drinkType.type + " -> Filling with max!");
		}
		this.addDrink(drinkType, missingAmount);
		from.removeDrink(drinkType, missingAmount);
	}

	/**
	 * Fills every drinktype of this location from certain location
	 * @param from source
	 * @throws Exception
	 */
	public void fillEveryDrinkFromLocation(Location from) throws Exception {
		for (DrinkType dt : this.getDrinkTypes()) {
			this.fillFromLocation(dt, from);
		}
	}

	/**
	 * Returns this location as a string
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(this.getName()+":\n");
		for (DrinkType dt : this.getDrinkTypes()) {
			out.append(dt.getType() + ": " + this.getDrinkAmount(dt) + "/" + this.getDrinkCapacity(dt) + " Bottles ("
					+ dt.bottlesToBoxes(this.getDrinkAmount(dt)) + "/" + dt.bottlesToBoxes(this.getDrinkCapacity(dt))
					+ " Boxes)\n");
		}
		return out.toString();
	}

  /**
	 * Returns the capacity for all types of drinks
	 * @return capacity
	 * @author Cedric Schmitt & J. Sebastian Kirner
	 */
	public Map<DrinkType, Integer> getCapacity() {
		return capacity;
	}

	/**
	 * Returns all available types of Drinks
	 * @return drinktypes
	 * @author Cedric Schmitt & J. Sebastian Kirner
	 */
	public Map<DrinkType, Integer> getDrinks() {
		return drinks;
	}

}
