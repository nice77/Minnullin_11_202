import java.util.Random;

public class Player {
	private int hp;
	private String name;
	public Player(String name) {
		this.hp = 100;
		this.name = name;
	}

	public static void hit(int dmg, Player s, Random r) {
		int chance = r.nextInt(10);
		if (chance <= 10 - dmg) {
			s.hp -= dmg;
		}
	}

	public int gethp() {
		return this.hp;
	}

	public String getname() {
		return this.name;
	}
}
