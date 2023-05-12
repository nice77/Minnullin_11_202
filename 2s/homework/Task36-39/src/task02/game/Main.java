package task02.game;


public class Main {
	public static void main(String[] args) {
		greeting();
		Game game = new Game();
		while (game.getRunning()) {
			game.step();
			game.checkPlayers();
		}
	}

	public static void greeting() {
		System.out.println("Welcome to fighting task02.game v1.0!");
	}
}
