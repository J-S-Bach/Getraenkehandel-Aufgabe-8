package inheritance;

import java.util.Objects;

public abstract class DrinkType {
	protected String type = "";

	protected int maxBottles = 0;
	//kasten = bottles/maxBottles

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
  
	public int boxesToBottles(int boxes) {
		return maxBottles * boxes;
	}

	/**
	 * Takes given bottles and returns boxes while ignoring rest bottles.
	 * @param bottles
	 * @return boxes
	 */
	public int bottlesToBoxes(int bottles) {
		return Math.floorDiv(bottles, maxBottles);
	}

	/**
	 *
	 * @param wantToAmount
	 * @return
	 */
	public int movableBottles(int wantToAmount) {
		return Math.floorDiv(wantToAmount, this.getMaxBottles()) * this.getMaxBottles();
	}

	public abstract String getAttributes();
}
