package inheritance;

public abstract class DrinkType {
	protected String type = "";

	protected int maxBottles = 0;
	//kasten = bottles/maxBottles

	public String getType() {
		return type;
	}

	public int getMaxBottles() {
		return maxBottles;
	}
  
	public int boxesToBottles(int boxes) {
		return maxBottles * boxes;
	}

	public int bottlesToBoxes(int bottles) {
		return Math.floorDiv(bottles, maxBottles);
	}

	public int movableBottles(int wantToAmount) {
		return Math.floorDiv(wantToAmount, this.getMaxBottles()) * this.getMaxBottles();
	}
}
