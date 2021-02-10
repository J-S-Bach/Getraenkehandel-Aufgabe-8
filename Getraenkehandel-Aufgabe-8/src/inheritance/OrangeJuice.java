package inheritance;

public class OrangeJuice extends DrinkType {
    private int fruitContent;

    public OrangeJuice(int fruitContent) {
        this.maxBottles = 6;
        this.bottles = maxBottles;
        this.type = "Orangensaft";
        this.fruitContent = fruitContent;
    }

    public int getFruitContent() {
        return fruitContent;
    }
}
