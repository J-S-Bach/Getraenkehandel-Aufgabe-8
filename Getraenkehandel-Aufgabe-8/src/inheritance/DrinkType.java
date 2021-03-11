package inheritance;

import java.util.Objects;

/**
 * Creates a class "DrinkType".
 * It contains standard methods for its subclasses like getter, setter and hashCode
 * just like special ones like boxesToBottles or bottlesToBoxes.
 * @author Cedric Schmitt & J. Sebastian Kirner
 */
public abstract class DrinkType {
	protected String type = "";

	protected int maxBottles = 0;


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DrinkType drinkType = (DrinkType) o;
		return type.equals(drinkType.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, maxBottles);
	}
	
	public String getType() {
		return type;
	}

	public int getMaxBottles() {
		return maxBottles;
	}

	/**
	 * Takes given boxes and returns bottles.
	 * @param boxes amount
	 * @return bottles amount
	 */
	public int boxesToBottles(int boxes) {
		return maxBottles * boxes;
	}

	/**
	 * Takes given bottles and returns boxes while ignoring rest bottles.
	 * @param bottles amount
	 * @return boxes amount
	 */
	public int bottlesToBoxes(int bottles) {
		return Math.floorDiv(bottles, maxBottles);
	}

	/**
	 * Takes given bottles and returns amount of bottles that are movable.
	 * @param wantToAmount given amount
	 * @return movable bottles
	 */
	public int movableBottles(int wantToAmount) {
		return Math.floorDiv(wantToAmount, this.getMaxBottles()) * this.getMaxBottles();
	}

	public abstract String getAttributes();
}
