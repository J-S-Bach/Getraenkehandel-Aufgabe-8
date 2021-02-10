package inheritance;

public class WaterNonSparkling extends DrinkType {
    private BottleType bottleType;

    public WaterNonSparkling(BottleType bottleType) {
        this.maxBottles = 6;
        this.type = "Mineralwasser ohne Kohlensäure";
        this.bottleType = bottleType;
    }

    public BottleType getBottleType() {
        return bottleType;
    }
}
