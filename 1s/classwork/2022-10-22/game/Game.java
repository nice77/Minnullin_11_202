import java.util.Scanner;
import java.util.Random;

public class Game {
	private Player uno, dos;
	private int turn;
	private Random r;
	private boolean isRunning;
	public Game (Player uno, Player dos) {
		this.uno = uno;
		this.dos = dos;
		this.turn = 1;
		this.r = new Random();
		this.isRunning = true;
	}

	public void step(Scanner sc) {
		int dmg = Integer.parseInt(sc.nextLine());
		if (this.turn == 1) {
			this.uno.hit(dmg, this.dos, this.r);
		}
		else {
			this.dos.hit(dmg, this.uno, this.r);
		}
		System.out.println(this.uno.getname() + " hp: " + this.uno.gethp());
		System.out.println(this.dos.getname() + " hp: " + this.dos.gethp());
		this.turn = (this.turn + 1) % 2;
	}

	public void check(Scanner sc) {
		char cmd;
		if (this.uno.gethp() <= 0 || this.dos.gethp() <= 0) {
			cmd = sc.nextLine().charAt(0);
			if (cmd == 'r') {
				System.out.println("restarting...");
				String name = sc.nextLine();
				this.uno = new Player(name);
				name = sc.nextLine();
				this.dos = new Player(name);
				this.restart(uno, dos);
			}
			else {
				System.out.println("Stopping...");
				this.isRunning = false;
			}
		}
	}

	public boolean getisrunning() {
		return this.isRunning;
	}

	public void restart(Player uno, Player dos) {
		this.uno = uno;
		this.dos = dos;
		this.turn = 1;
	}
}
