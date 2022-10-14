import java.util.Scanner;

public class Task09 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = Integer.parseInt(args[0]);
		int w = Integer.parseInt(args[1]);
		String[][] data = new String[h][w];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				data[i][j] = sc.nextLine();
			}
		}

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println(checker(data));

	}

	public static boolean checker(String[][] data) {
		for (int i = 0; i < data[0].length; i++) {
			if (!starter(data, i)) {
				return false;
			}
		}
		return true;
	}

	public static boolean starter(String[][] data, int i) {
		int c = 0;
		for (int j = 0; j < data.length; j++) {
			if (ifStarts(data[j][i])) {
				c++;
			}
			if (c > 2) {
				return false;
			}
		}
		return true;
	}

	public static boolean ifStarts(String elem) {
		String vowels = new String("aeiouyAEIOUY");
		for (int k = 0; k < 12; k++) {
			if (elem.charAt(0) == vowels.charAt(k)) {
				return true;
			}
		}
		return false;
	}

}
