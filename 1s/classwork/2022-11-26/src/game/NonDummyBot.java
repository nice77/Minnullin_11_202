package game;

import java.util.Random;

public class NonDummyBot extends Player {
	private Random r = new Random();
	public NonDummyBot(String name) {
		super(name);
	}

	public int decide(Player p) {
		if (p.getHP() < 5) {
			return p.getHP();
		}
		else {
			int rad = ((p.getHP() < 9) ? p.getHP() : 9);
			return r.nextInt(rad) + 1;
		}
	}
}
