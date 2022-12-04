package game;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
	private boolean isRunning, unoTurn;
	private Random r = new Random();
	private Scanner sc = new Scanner(System.in);
	private Player uno;
	private Player[] dos;
	public Game(int gameMode, int playerNum) {
		this.isRunning = true;
		System.out.print("Input your name: ");
		this.uno = new HumanPlayer(sc.nextLine());
		this.dos = new Player[playerNum];
		if (gameMode == 3) {
			for(int i = 0; i < playerNum; i++) {
				this.dos[i] = (r.nextInt(2) == 0) ? new DummyBot("DummyBot " + i) : new NonDummyBot("NonDummyBot " + i);
			}
		}
		else {
			if (gameMode == 0) {
				System.out.print("Input your name: ");
			}
			this.dos[0] = (gameMode == 0) ? new HumanPlayer(sc.nextLine()) : ((gameMode == 1) ? new DummyBot("0") : new NonDummyBot("0"));
		}
	}

	public boolean getRunning() {
		return this.isRunning;
	}

	public void step() {
		if (this.unoTurn) {
			if (this.dos.length != 1) {
				System.out.print("Input position of bot you want to hit: ");
			}
			int place = (this.dos.length == 1) ? 0 : Integer.parseInt(sc.nextLine());
			this.uno.kick(this.dos[place]);
		}
		else {
			for (int i = 0; i < this.dos.length; i++) {
				this.dos[i].kick(this.uno);
			}
		}
		this.unoTurn = !unoTurn;
	}
	public void checkPlayers() {
		if (!this.isUnoAlive() || !this.isDosAlive()) {
			this.isRunning = false;
		}
	}

	public boolean isUnoAlive() {
		return this.uno.getHP() > 0;
	}
	public boolean isDosAlive() {
		for(int i = 0; i < this.dos.length; i++) {
			if (this.dos[i].getHP() > 0) {
				return true;
			}
		}
		return false;
	}
}
