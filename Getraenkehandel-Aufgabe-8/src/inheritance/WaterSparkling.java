package inheritance;

public class WaterSparkling extends DrinkType {
    private BottleType bottleType;

    public WaterSparkling(BottleType bottleType) {
        this.maxBottles = 12;
        this.bottles = maxBottles;
        this.type = "Mineralwasser mit Kohlensäure";
        this.bottleType = bottleType;
    }

    public BottleType getBottleType() {
        return bottleType;
    }
}
