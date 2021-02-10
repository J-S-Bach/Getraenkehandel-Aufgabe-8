package inheritance;

public class Lemonade extends DrinkType {
    private int fruitContent;

    public Lemonade(int fruitContent) {
        this.maxBottles = 12;
        this.type = "Limonade";
        this.fruitContent = fruitContent;
    }

    public int getFruitContent() {
        return fruitContent;
    }
}
