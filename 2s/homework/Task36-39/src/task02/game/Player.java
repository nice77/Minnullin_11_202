package task02.game;

public abstract class Player {
	private int hp;
	private final String name;
	public Player(String name) {
		this.hp = 100;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getHP() {
		return this.hp;
	}

	public void setHP(int val) {
		this.hp = val;
	}

	public abstract int decide(Player k);

	public void kick(Player p) {
		int k = this.decide(p);

		if (chanceCreator(k)) {
			System.out.println(this.name + " hits ---> " + k);
			//p.setHP(p.getHP() - k);
			p.hp -= k;
			System.out.println("Player " + p.getName() + "'s health: " + p.getHP());
			System.out.println("Player " + this.name + "'s health: " + this.hp);
		}
		else {
			System.out.println("Player " + this.name + " misses!");
		}
	}

	private boolean chanceCreator(int k) {
		return Math.random() < 1 - 0.1 * k;
	}
	public String toString() {
		return this.name;
	}
}
