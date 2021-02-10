package inheritance;

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
