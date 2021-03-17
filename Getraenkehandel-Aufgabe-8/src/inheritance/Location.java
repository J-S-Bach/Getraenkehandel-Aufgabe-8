package inheritance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Location class
 * 
 * @author Simon Hoim
 * @author Felix Köhler
 */
public class Location {
	protected String name = "";
	protected Map<DrinkType, Integer> capacity = new HashMap<>();
	protected Map<DrinkType, Integer> drinks = new HashMap<>();

	/**
	 * Creates location object
	 * 
	 * @param name Name of this Location
	 */
	public Location(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of this location
	 * 
	 * @return name name of this location
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name of this location
	 * 
	 * @param name Name of this location
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the capacity for this location
	 * 
	 * @param drinkType Specific DrinkType
	 * @param amount Max Capacity
	 * @throws Exception Error if negative value
	 */
	public void setCapacity(DrinkType drinkType, int amount) throws Exception {
		if (amount < 0)
			throw new Exception("Negative value not valid!");
		capacity.put(drinkType, amount);
		this.drinks.put(drinkType, 0);
	}

	/**
	 * Returns the capacity of this location
	 * 
	 * @param drinkType Specific DrinkType
	 * @return capacity Capacity of this DrinkType
	 */
	public int getDrinkCapacity(DrinkType drinkType) {
		Integer a = capacity.get(drinkType);
		return a;
	}

	/**
	 * Returns all drinktypes that can be stored in this location
	 * 
	 * @return drinktypes all DrinkTypes in this location
	 */
	public DrinkType[] getDrinkTypes() {
		return drinks.keySet().toArray(new DrinkType[drinks.keySet().size()]);
	}

	/**
	 * Returns amount of drinks stored in this location
	 * 
	 * @param drinkType Specific DrinkType
	 * @return amount Stored bottles
	 */
	public int getDrinkAmount(DrinkType drinkType) {
		return drinks.get(drinkType);
	}

	/**
	 * Adds drink to the storage of this location
	 * 
	 * @param drinkType Specific DrinkType
	 * @param amount Bottles to add
	 * @return success True if adding succeeded
	 * @throws Exception Error if negative value
	 */
	public boolean addDrink(DrinkType drinkType, int amount) throws Exception {
		if (amount < 0)
			throw new Exception("Negative value not valid!");
		if (this.getDrinkCapacity(drinkType) < this.getDrinkAmount(drinkType) + amount)
			return false; // not enough capacity
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) + amount);
		return true;
	}

	/**
	 * Removes drink from the storage of this location
	 * 
	 * @param drinkType Specific DrinkType
	 * @param amount Bottles to remove
	 * @return success True if removing succeeded
	 * @throws Exception Error if negative value
	 */
	public boolean removeDrink(DrinkType drinkType, int amount) throws Exception {
		if (amount < 0)
			throw new Exception("Negative value not valid!");
		if (this.getDrinkAmount(drinkType) < amount)
			return false; // not enough to remove
		this.drinks.put(drinkType, this.getDrinkAmount(drinkType) - amount);
		return true;
	}

	/**
	 * Returns the amount of bottles that are missing for this location to be full
	 * of the certain drinktype
	 * 
	 * @param drinkType Specific DrinkType
	 * @return amount Bottles that are missing for the storage to be full
	 */
	public int getMissing(DrinkType drinkType) {
		return this.getDrinkCapacity(drinkType) - this.getDrinkAmount(drinkType);
	}

	/**
	 * Moves drinks from this locations
	 * 
	 * @param to Location where drink should be moved to
	 * @param drinkType Specific DrinkType
	 * @param boxes Amount of boxes to move
	 * @return success True if moving succeeded
	 * @throws Exception Error if negative value
	 */
	public boolean moveDrinks(Location to, DrinkType drinkType, int boxes) throws Exception {
		if (boxes < 0)
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
	 * 
	 * @param drinkType Specific DrinkType
	 * @param from Source location where drinks are taken from
	 * @throws Exception Error if negative value
	 */
	public void fillFromLocation(DrinkType drinkType, Location from) throws Exception {
		int missingAmount = drinkType.movableBottles(this.getMissing(drinkType));
		if (from.getDrinkAmount(drinkType) < missingAmount) {
			missingAmount = from.getDrinkAmount(drinkType); // set amount to max of "from"
			throw new Exception("Not enough " + drinkType.type + " -> Filling with max!");
		}
		this.addDrink(drinkType, missingAmount);
		from.removeDrink(drinkType, missingAmount);
	}

	/**
	 * Fills every drinktype of this location from certain location
	 * 
	 * @param from Source location where drinks are taken from
	 * @throws Exception Error if negative value
	 */
	public void fillEveryDrinkFromLocation(Location from) throws Exception {
		for (DrinkType dt : this.getDrinkTypes()) {
			this.fillFromLocation(dt, from);
		}
	}

	/**
	 * Returns the amount of glass and plastic bottles of this location as an integer array
	 * @return [glassBoxes, plasticBottles] array with amount of glass and plastic bottles
	 */
	public int[] getBottleTypeBoxes() {
		int glassBoxes = 0;
		int plasticBoxes = 0;
		for (Map.Entry<DrinkType, Integer> entry : drinks.entrySet()) {
			if (entry.getKey() instanceof WaterNonSparkling) {
				if (((WaterNonSparkling) entry.getKey()).getBottleType().equals(BottleType.GLAS))
					glassBoxes += entry.getKey().movableBottles(entry.getValue());
				else
					plasticBoxes += entry.getKey().movableBottles(entry.getValue());
			}
			if (entry.getKey() instanceof WaterSparkling) {
				if (((WaterSparkling) entry.getKey()).getBottleType().equals(BottleType.GLAS))
					glassBoxes += entry.getKey().movableBottles(entry.getValue());
				else
					plasticBoxes += entry.getKey().movableBottles(entry.getValue());
			}
		}
		return new int[] { glassBoxes, plasticBoxes };
	}

	/**
	 * Returns this location as a string
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(this.getName() + ":\n");
		for (DrinkType dt : this.getDrinkTypes()) {
			out.append(dt.getType() + ": " + this.getDrinkAmount(dt) + "/" + this.getDrinkCapacity(dt) + " Bottles ("
					+ dt.bottlesToBoxes(this.getDrinkAmount(dt)) + "/" + dt.bottlesToBoxes(this.getDrinkCapacity(dt))
					+ " Boxes)\n");
		}
		return out.toString();
	}

	/**
	 * Returns the capacity for all types of drinks
	 * 
	 * @return capacity
	 * @author Cedric Schmitt & J. Sebastian Kirner
	 */
	public Map<DrinkType, Integer> getCapacity() {
		return capacity;
	}

	/**
	 * Returns all available types of Drinks
	 * 
	 * @return drinktypes
	 * @author Cedric Schmitt & J. Sebastian Kirner
	 */
	public Map<DrinkType, Integer> getDrinks() {
		return drinks;
	}

}
