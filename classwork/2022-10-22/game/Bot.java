import java.util.Random;

public class Bot {
	private int hp;
	private String name;
	private boolean goodhit;
	public Bot(String name) {
		this.hp = 100;
		this.name = name;
		this.goodhit = false;
	}

	public void hit(int dmg, Player player, Random r) {
		int chance = r.nextInt(10);
                if (chance <= 10 - dmg) {
                        player.hp -= dmg;
                }
	}
}
