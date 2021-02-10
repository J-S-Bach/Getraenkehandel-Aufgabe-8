package inheritance;

public class Beer extends DrinkType {
    private int alcoholicContent;

    public Beer(int alcoholicContent) {
        this.maxBottles = 24;
        this.bottles = maxBottles;
        this.type = "Apfelsaft";
        this.alcoholicContent = alcoholicContent;
    }

    public int getAlcoholicContent() {
        return alcoholicContent;
    }
}
