package inheritance;

/**
 * Creates a new subclass "WaterNonSparkling" for DrinkType with given properties.
 * @see DrinkType
 */
public class WaterNonSparkling extends DrinkType {
    private final BottleType bottleType;

    /**
     * Creates properties "maxBottles", "type" and "bottleType".
     * @param bottleType kind of bottle (glass or plastic)
     */
    public WaterNonSparkling(BottleType bottleType) {
        this.maxBottles = 6;
        this.type = "Mineralwasser ohne Kohlensäure";
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
