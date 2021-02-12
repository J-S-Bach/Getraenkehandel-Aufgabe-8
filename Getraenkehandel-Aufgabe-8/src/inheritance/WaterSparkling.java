package inheritance;

public class WaterSparkling extends DrinkType {
    private final BottleType bottleType;

    public WaterSparkling(BottleType bottleType) {
        this.maxBottles = 12;
        this.type = "Mineralwasser mit Kohlensäure";
        this.bottleType = bottleType;
    }

    public BottleType getBottleType() {
        return bottleType;
    }

    @Override
    public String getAttributes() {
        return "Flaschentyp: " + getBottleType();
    }
}
