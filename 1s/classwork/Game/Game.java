import java.util.Scanner;
import java.util.Random;

public class Game {
	public static void main(String[] args) {
		Player player1 = new Player(false);
		Player[] player2 = ((args.length != 0) ? new Player[Integer.parseInt(args[0])] : new Player[1]);
		for (int i = 0; i < player2.length; i++) {
			player2[i] = new Player(true);
		}
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		gameCycle(player1, player2, r, sc, args);
	}

	public static void gameCycle(Player player1, Player[] player2, Random r, Scanner sc, String[] args) {
		int power;
		int bot = 0, stupid = 0, botStepCount = 0;
		boolean firstPlayer = true;
		int lostPlayer2 = 0;
		int choose;
		if (args.length != 0) {
			bot = Integer.parseInt(args[0]);
			stupid = Integer.parseInt(args[1]);
		}

		while (player1.hp != 0 && lostPlayer2 != player2.length) {
			if (firstPlayer) {
				System.out.println("Player1 hits!");
				do {
					System.out.print("Input power: ");
					power = sc.nextInt();
				}
				while (power < 1 || 9 < power);
				do {
					choose = sc.nextInt();
				}
				while (0 > choose || choose >= player2.length);
				player2[choose].hit(power);
				if (player2[choose].hp == 0) {
					lostPlayer2++;
				}
				for (Player p : player2) {
					System.out.print(p.hp + " " + p.gotDamage + " | ");
				}
			}
			else {
				System.out.println("Player2 strikes back!");
				if (bot != 0) {
					if (stupid == 0) {
						for (int i = 0; i < player2.length; i++) {
							if (player2[i].hp != 0) {
								power = r.nextInt(10);
								player1.hit(power);
							}
						}
					}
					else {
						for (int i = 0; i < player2.length; i++) {
							if (player2[i].hp != 0) {
								if (botStepCount != 0) {
									player2[i].botpower = ((player2[i].gotDamage) ? player2[i].botpower + 1 : player2[i].botpower - 1);
									player2[i].botpower = ((player2[i].botpower > 9) ? 9 : player2[i].botpower);
									player2[i].botpower = ((player2[i].botpower < 1) ? 1 : player2[i].botpower);
								}
								power = player2[i].botpower;
								player2[i].gotDamage = player1.hit(power);
							}
						}
					}
				}
				else {
					do {
						System.out.print("Input power: ");
						power = sc.nextInt();
					}
					while (0 >= power || power >= 10);
				}
				botStepCount++;
			}
			firstPlayer = !firstPlayer;
			System.out.println();
		}
	}
}
