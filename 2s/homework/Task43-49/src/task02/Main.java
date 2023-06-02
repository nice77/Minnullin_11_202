package task02;

public class Main {
    public static void main(String[] args) {
        BonusCard bc = new BonusCard(150);
        Human husband = new Human(bc, "John");
        Human wife = new Human(bc, "Mary");
        husband.start();
        wife.start();
    }
}
