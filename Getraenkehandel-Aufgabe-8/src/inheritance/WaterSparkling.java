package inheritance;

/**
 * Creates a new subclass "WaterSparkling" for DrinkType with given properties.
 * @see DrinkType
 */
public class WaterSparkling extends DrinkType {
    private final BottleType bottleType;

    /**
     * Creates properties "maxBottles", "type" and "bottleType".
     * @param bottleType kind of bottle (glass or plastic)
     */
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
