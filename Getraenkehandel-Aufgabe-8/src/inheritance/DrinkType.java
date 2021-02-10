package inheritance;

public abstract class DrinkType {
	protected String type = "";
	protected int maxBottles = 0;	//per box
	protected int bottles = 0;
	//kasten = bottles/maxBottles


	public String getType() {
		return type;
	}

	public int getMaxBottles() {
		return maxBottles;
	}

	public int getBottles() {
		return bottles;
	}

	public void setBottles(int bottles) {
		this.bottles = Math.min(bottles, maxBottles);
	}
	
	public int boxesToBottles(int boxes) {
		return maxBottles*boxes;
	}
	
	public int movableBottles(int wantToAmount) {
		return Math.floorDiv(wantToAmount, this.getMaxBottles())*this.getMaxBottles();
	}
}
