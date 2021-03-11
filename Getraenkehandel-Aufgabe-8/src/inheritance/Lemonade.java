package inheritance;

/**
 * Creates a new subclass "Lemonade" for DrinkType with given properties.
 * @see DrinkType
 * @author Cedric Schmitt & J. Sebastian Kirner
 */
public class Lemonade extends DrinkType {
    private final int fruitContent;

    /**
     * Creates properties "maxBottles", "type" and "fruitContent".
     * @param fruitContent amount in percent
     * @author Cedric Schmitt & J. Sebastian Kirner
     */
    public Lemonade(int fruitContent) {
        this.maxBottles = 12;
        this.type = "Limonade";
        this.fruitContent = fruitContent;
    }

    public int getFruitContent() {
        return fruitContent;
    }

    @Override
    public String getAttributes() {
        return "Fruchtgehalt: " + getFruitContent() + "%";
    }
}
