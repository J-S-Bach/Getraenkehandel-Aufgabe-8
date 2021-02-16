package inheritance;

/**
 * Creates a new subclass "Beer" for DrinkType with given properties.
 * @see DrinkType
 */
public class Beer extends DrinkType {
    private final int alcoholicContent;

    /**
     * Creates properties "maxBottles", "type" and "alcoholicContent".
     * @param alcoholicContent amount in percent
     */
    public Beer(int alcoholicContent) {
        this.maxBottles = 24;
        this.type = "Bier";
        this.alcoholicContent = alcoholicContent;
    }

    public int getAlcoholicContent() {
        return alcoholicContent;
    }

    @Override
    public String getAttributes() {
        return "Alkoholgehalt: " + getAlcoholicContent() + "%";
    }
}
