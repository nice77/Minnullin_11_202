package task02;

public class BonusCard {
    private int bonuses;
    public int getBonuses() {return bonuses; }
    public BonusCard(int bonuses) {
        this.bonuses = bonuses;
    }
    public boolean use(int n) {
        if (bonuses >= n) {
            bonuses -= n;
            System.out.println(bonuses + " left.");
            return true;
        }
        else {
            throw new RuntimeException("OOOOOPS");
        }
    }
}
