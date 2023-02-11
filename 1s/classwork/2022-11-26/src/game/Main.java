package game;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		greeting();
		Game game = sessionCreator();
		while (game.getRunning()) {
			game.step();
			game.checkPlayers();
		}
	}

	public static void greeting() {
		System.out.println("Welcome to fighting game v1.0!");
                System.out.println("Choose a gamemode you would like to try:");
                System.out.println("0 - against human;");
                System.out.println("1 - against one dummy bot;");
                System.out.println("2 - against NonDummyBot;");
                System.out.println("3 - against multiple number of random types of bots");
	}
	public static Game sessionCreator() {
		Scanner sc = new Scanner(System.in);
		int gameMode = sc.nextInt();
		if (gameMode == 3) {
			System.out.println("Input num of enemies");
		}
		int playerNum = (gameMode == 3) ? sc.nextInt() : 1;
		return new Game(gameMode, playerNum);
	}
}
