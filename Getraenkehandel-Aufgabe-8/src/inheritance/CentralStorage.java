package inheritance;

public class CentralStorage extends Location{

	public CentralStorage(String name) {
		super(name);
		/*this.setCapacity( , 200);
		this.setCapacity( , 400);
		this.setCapacity( , 200);
		this.setCapacity( , 300);
		this.setCapacity( , 200);
		this.fill();
		*/
		System.out.println("Capacitiy missing");
	}
	
	public void fill() {	//on startup after capacity is set
		drinks = capacity;
	}
	
	public void moveDrinks(Location loc, DrinkType drinkType, int boxes) {
		//loc.addDrink(drinkType, drinkType.)
	}
}
