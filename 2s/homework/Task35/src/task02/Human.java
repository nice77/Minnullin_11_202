package task02;

class Human extends Thread {
    private final BonusCard bonusCard;
    private String who;
    public Human(BonusCard bc, String who) {
        this.bonusCard = bc;
        this.who = who;
    }
    public void shoppingWithBonuses(int bonuses) {
        synchronized (bonusCard) {
            if (bonusCard.getBonuses() >= bonuses) {
                System.out.println(who + " is gonna buy something.");
                if (bonusCard.use(bonuses))
                    System.out.println(who + " bought something.");
            } else {
                System.out.println("Sorry, Honey, no money.");
            }
        }
    }
    public void run() {
        for (int i = 0; i < 100; i++) {
            shoppingWithBonuses(7);
        }
    }
}
