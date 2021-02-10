package inheritance;

public class CentralStorage extends Location{

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
	
	public void fill() {	//on startup after capacity is set
		drinks = capacity;
	}
}
