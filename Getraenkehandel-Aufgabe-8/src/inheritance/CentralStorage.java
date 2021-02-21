package inheritance;

public class CentralStorage extends Location{

	public CentralStorage(String name) {
		super(name);
		this.setCapacity(new WaterNonSparkling(BottleType.GLAS), 198);
		this.setCapacity(new WaterSparkling(BottleType.PLASTIC), 396);
		this.setCapacity(new AppleJuice(30), 198);
		this.setCapacity(new OrangeJuice(40), 396);
		this.setCapacity(new Lemonade(35), 300);
		this.setCapacity(new Beer(7), 192);
		this.fill();
	}
	
	public void fill() {	//on startup after capacity is set
		for(DrinkType dt : this.getDrinkTypes()) {
			this.addDrink(dt, this.getDrinkCapacity(dt));
		}
	}
}
