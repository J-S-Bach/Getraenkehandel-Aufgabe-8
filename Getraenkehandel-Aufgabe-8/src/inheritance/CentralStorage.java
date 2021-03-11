package inheritance;

/**
 * CentralStorage class
 * @author Simon Hoim
 * @author Felix Köhler
 */
public class CentralStorage extends Location{

	/**
	 * Creates CentralStorage object and assigns drinktype capacities
	 * @param name name of CentralStorage
	 */
	public CentralStorage(String name) {
		super(name);
		this.setCapacity(new WaterNonSparkling(BottleType.GLAS), 200);
		this.setCapacity(new WaterSparkling(BottleType.PLASTIC), 400);
		this.setCapacity(new AppleJuice(30), 200);
		this.setCapacity(new OrangeJuice(40), 400);
		this.setCapacity(new Lemonade(35), 300);
		this.setCapacity(new Beer(7), 200);
		this.fill();
	}
	
	/**
	 * Completely fills this central storage
	 */
	public void fill() {	//on startup after capacity is set
		for(DrinkType dt : this.getDrinkTypes()) {
			this.addDrink(dt, this.getDrinkCapacity(dt));
		}
	}
}
