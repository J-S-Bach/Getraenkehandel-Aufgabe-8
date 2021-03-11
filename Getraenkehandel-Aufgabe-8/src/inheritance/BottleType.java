package inheritance;

/**
 * Defines possible materials.
 * @see WaterNonSparkling
 * @see WaterSparkling
 */
public enum BottleType {
    GLAS("Glas"), PLASTIC("Plastik");

    private final String name;
    BottleType(String s) {
        name = s;
    }
    public String toString() {
        return this.name;
    }
}
