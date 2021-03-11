package inheritance;

/**
 * Creates a new subclass "AppleJuice" for DrinkType with given properties.
 * @see DrinkType
 * @author Cedric Schmitt & J. Sebastian Kirner
 */
public class AppleJuice extends DrinkType {
    private final int fruitContent;

    /**
     * Creates properties "maxBottles", "type" and "fruitContent".
     * @param fruitContent amount in
     * @author Cedric Schmitt & J. Sebastian Kirner
     */
    public AppleJuice(int fruitContent) {
        this.maxBottles = 6;
        this.type = "Apfelsaft";
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
