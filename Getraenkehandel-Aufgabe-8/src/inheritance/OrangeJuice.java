package inheritance;

public class OrangeJuice extends DrinkType {
    private final int fruitContent;

    public OrangeJuice(int fruitContent) {
        this.maxBottles = 6;
        this.type = "Orangensaft";
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
