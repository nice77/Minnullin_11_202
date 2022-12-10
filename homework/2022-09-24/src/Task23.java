import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class Task23 {
	public static void main(String[] args) {
//----------init----------
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		if (args.length != 0) {
			if (args[0].equals("-h")) {
				System.out.println("To open the cell - type 'o {x} {y}'");
				System.out.println("To put the flag - type 'f {x} {y}'");
			}
		}
		System.out.print("Input field's width: ");
		final int WIDTH = Integer.parseInt(sc.nextLine());
		System.out.print("Input field's height: ");
		final int HEIGHT = Integer.parseInt(sc.nextLine());
		System.out.print("Input number of mines: ");
		final int MINES = Integer.parseInt(sc.nextLine());
		final int TAB = 8;

		int[][] field = new int[HEIGHT][WIDTH];
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
		}

		int[][] opened = new int[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				opened[i][j] = 0;
			}
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

			System.out.println(" mmmm");
			System.out.println("#'   '  mmm   mmmm   m   m   mmm    m mm");
			System.out.println("'#mmm  '   #  #' '#  'm m'  #' '#   #'");
			System.out.println("    '# m'''#  #   #   #m#   #   #   #");
			System.out.println("'mmm#' 'mm'#  ##m#'   '#    '#m#'   #");
			System.out.println("              #       m");
			System.out.println("An innovative game!\n");

			//	outputting the gameboard
			System.out.print(repeat(" ", TAB - 1) + "|");
			//---
			for (int i = 0; i < WIDTH; i++) {
				System.out.print(repeat(" ", (TAB / 2) - 2) + i + repeat(" ", (TAB / 2) - 1) + "|\t");
			}
			System.out.println();
			for (int i = 0; i < HEIGHT; i++) {
				System.out.println(repeat("-", TAB * (1 + WIDTH)));
				System.out.print(repeat(" ", 3) + i + repeat(" ", 3) + "|");
				for (int j = 0; j < WIDTH; j++) {
					if (opened[i][j] == 0) {
						System.out.print(repeat(" ", (TAB / 2) - 2) + "#" + repeat(" ", (TAB / 2) - 1) + "|\t");
						leftCells += 1;
					} else if (opened[i][j] == 1) {
						System.out.print((field[i][j] == -1) ? repeat(" ", (TAB / 2) - 2) + "b" + repeat(" ", (TAB / 2) - 1) + "|\t" : repeat(" ", (TAB / 2) - 2) + "\u001B[32m" + field[i][j] + "\u001B[0m" + repeat(" ", (TAB / 2) - 1) + "|\t");
						if (field[i][j] == -1) {
							running = false;
						}
					} else if (opened[i][j] == 2) {
						System.out.print(repeat(" ", (TAB / 2) - 2) + "F" + repeat(" ", (TAB / 2) - 1) + "|\t");
					}
				}
				System.out.println();
			}
			System.out.println(repeat("-", TAB * (WIDTH + 1)));
			if (leftCells == MINES) {
				running = false;
				won = true;
			}
			if (!running) {
				System.out.println((won) ? "You've won!" : "A bomb!");
				break;
			}
			leftCells = 0;

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
									for (int[] arr : queue) {
										if (arr[0] == elem[0] && arr[1] == elem[1]) {
											isnotin = false;
										}
									}
									if (isnotin) {
										queue.offer(Arrays.copyOf(elem, elem.length));
									} else {
										isnotin = true;
									}
									elem[0] -= dx;
									elem[1] -= dy;
								}
							}
						}
					}
				}
			}
			// putting a flag on the cell we selected
			else if (cmd[0].equals("f")) {
				opened[coords[1]][coords[0]] = ((opened[coords[1]][coords[0]] == 2) ? 0 : 2);
			}
			System.out.println("\033[2J");
		}
	}

	public static String repeat(String ref, int num) {
		StringBuilder out = new StringBuilder();
		for (int i = 0; i < num; i++) {
			out.append(ref);
		}
		return out.toString();
	}
}
