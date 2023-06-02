package task02.game;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.Scanner;

public class Game {
	private boolean isRunning, unoTurn;
	private Random r = new Random();
	private Scanner sc = new Scanner(System.in);
	private Player uno;
	private Player dos;
	public Game() {
		this.isRunning = true;
		System.out.print("Input your name: ");
		this.uno = new HumanPlayer(sc.nextLine());
		this.dos = fabric();
	}

	private Player fabric() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/task02/input.txt")));
			String i = br.readLine();
			br.close();
			Class<? extends Player> cl = (Class<? extends Player>) Class.forName(i);
			return cl.getConstructor(String.class).newInstance(i);
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException |
				 NoSuchMethodException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean getRunning() {
		return this.isRunning;
	}

	public void step() {
		if (this.unoTurn) {
			this.uno.kick(this.dos);
		}
		else {
			this.dos.kick(this.uno);
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
		return this.dos.getHP() > 0;
	}
}
