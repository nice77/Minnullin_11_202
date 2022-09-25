import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class Task02 {
    public static void main(String[] args) {
//----------init----------
	Scanner sc = new Scanner(System.in);
	Random r = new Random();
	final int WIDTH = 10;
	final int HEIGHT = 10;
	final int MINES = 10;
	final int TAB = 8;

	int [][] field = new int[HEIGHT][WIDTH];
	for (int i = 0; i < HEIGHT; i++) {
	    for (int j = 0; j < WIDTH; j++) {
		field[i][j] = 0;
	    }
	}
	for (int i = 0; i < MINES; i++) {
	    int x, y;
	    do {
		x = r.nextInt(WIDTH);
		y = r.nextInt(HEIGHT);
	    }
	    while (field[y][x] == -1);
	    field[y][x] = -1;
	    int[] deltas = {-1, 0, 1};
	    for (int dy : deltas) {
		if (y + dy < 0 || y + dy >= HEIGHT) {
		    continue;
		}
		for (int dx : deltas) {
		    if (dy == 0 && dx == 0) {
			continue;
		    }
		    if (x + dx < 0 || x + dx >= WIDTH) {
			continue;
		    }
		    if (field[y + dy][x + dx] != -1) {
			field[y + dy][x + dx] += 1;
		    }
		}
	    }
	    /*
	    bombs' positions
	    System.out.println("x, y: " + x + " " + y);
	    */
	}

	int [][] opened = new int[HEIGHT][WIDTH];
	for (int i = 0; i < HEIGHT; i++) {
	    for (int j = 0; j < WIDTH; j++) {
		opened[i][j] = 0;
		/*
		field
		System.out.print(field[i][j] + "\t");
		*/
	    }
	    //System.out.println();
	}

//----------game cycle----------
	boolean running = true;
	boolean won = false;
	int leftCells = 0;
	String[] cmd;
	int[] coords = new int[2];
	String data;
	int[] elem;
	Queue<int[]> queue = new LinkedList<>();
	int[] deltas = {-1, 0, 1};
	boolean isnotin = true;
	while (running) {
	    //	outputting the gameboard
	    for (int i = 0; i < HEIGHT; i++) {
		System.out.println("−".repeat(TAB * WIDTH));
		for (int j = 0; j < WIDTH; j++) {
		    if (opened[i][j] == 0) {
			System.out.print(" ".repeat(TAB / 2 - 2) + "#" + " ".repeat(TAB / 2 - 1) + "|\t");
			leftCells += 1;
		    }
		    else if (opened[i][j] == 1) {
			System.out.print((field[i][j] == -1) ? " ".repeat(TAB / 2 - 2) + "b" + " ".repeat(TAB / 2 - 1) + "|\t" : " ".repeat(TAB / 2 - 2) + "\u001B[32m" + field[i][j] + "\u001B[0m" + " ".repeat(TAB / 2 - 1) + "|\t");
			if (field[i][j] == -1){
			    running = false;
			}
		    }
		    else if (opened[i][j] == 2) {
			System.out.print(" ".repeat(TAB / 2 - 2) + "F" + " ".repeat(TAB / 2 - 1) + "|\t");
		    }
		}
		System.out.println();
	    }
	    System.out.println("−".repeat(TAB * WIDTH));
	    if (leftCells == MINES) {
		running = false;
		won = true;
	    }
	    if (running == false) {
		System.out.println((won) ? "You've won!" : "A bomb!");
		break;
	    }

	    //	parsing the commands

	    data = sc.nextLine();
	    cmd = data.split(" ");
	    coords[0] = Integer.parseInt(cmd[1]);
	    coords[1] = Integer.parseInt(cmd[2]);

	    // 	opening the cell we selected

	    if (cmd[0].equals("o")) {
		queue.add(coords);
		while (queue.size() != 0) {
		    elem = queue.remove();
		    opened[elem[1]][elem[0]] = 1;
		    if (field[elem[1]][elem[0]] != 0) {
			continue;
		    }
		    for (int dy : deltas) {
			if (elem[1] + dy < 0 || elem[1] + dy >= HEIGHT) {
			    continue;
			}
			for (int dx : deltas) {
			    if (elem[0] + dx < 0 || elem[0] + dx >= WIDTH) {
				continue;
			    }
			    if (dx == 0 && dy == 0) {
				continue;
			    }
			    if (opened[elem[1] + dy][elem[0] + dx] != 1) {
				if (field[elem[1] + dy][elem[0] + dx] >= 0) {
				    elem[0] += dx;
				    elem[1] += dy;
				    System.out.println("Comparing: ");
				    for (int[] arr : queue) {
					if (arr[0] == elem[0] && arr[1] == elem[1]) {
					    isnotin = false;
					}
				    }
				    if (isnotin) {
				    	queue.offer(Arrays.copyOf(elem, elem.length));
				    }
				    else { isnotin = true; }
				    // Добавляет массивы с разными элементами, но с одними id! Надо изменить проверку!
				    // В очереди сидят переменные elem, которые мы меняем => меняем и сами элементы очереди!
				    // Проще говоря, надо помнить про ссылочные и не только типы данных
				    System.out.println("Dx, dy, elem: " + dx + " " + dy + " " + Arrays.toString(elem));
				    elem[0] -= dx;
				    elem[1] -= dy;
				}
			    }
			}
		    }
		}
	    }
	    // putting a flag on the cel we selected
	    else if (cmd[0].equals("f")) {
		opened[coords[1]][coords[0]] = ((opened[coords[1]][coords[0]] == 2) ? 0 : 2);
	    }
	    System.out.println("\033[2J");
	}
    }
}
