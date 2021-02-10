package inheritance;

public class Beer extends DrinkType {
    private int alcoholicContent;

    public Beer(int alcoholicContent) {
        this.maxBottles = 24;
        this.type = "Bier";
        this.alcoholicContent = alcoholicContent;
    }

    public int getAlcoholicContent() {
        return alcoholicContent;
    }
}
