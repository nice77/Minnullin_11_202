import java.util.Random;

public class Player {
	int hp;
	boolean gotDamage;
	int botpower;
	boolean bot;
	public Player (boolean b) {
		this.hp = 100;
		this.gotDamage = false;
		this.botpower = 5;
	}
	public boolean hit(int power) {
		Random r = new Random();
		int chance = r.nextInt(10);
		System.out.println("Chance: " + chance);
		System.out.println("Power: " + power);
		if (0 <= chance && chance <= 10 - power) {
			this.hp -= power;
			System.out.println("Left hp: " + this.hp);
			return true;
		}
		System.out.println("Left hp: " + this.hp);
		return false;
	}
}
