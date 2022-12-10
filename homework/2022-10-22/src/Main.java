import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		Player uno = new Player(sc.nextLine());
		Player dos = new Player(sc.nextLine());
		Game game = new Game(uno, dos);

		while (game.getisrunning()) {
			game.step(sc);
			game.check(sc);
		}
	}
}
