package inheritance;

public class AppleJuice extends DrinkType {
    private final int fruitContent;

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
