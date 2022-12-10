import java.util.Scanner;

public class Task30 {
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
		String vowels = "aeiouyAEIOUY";
		for (int j = 0; j < data.length; j++) {
			for (int k = 0; k < vowels.length(); k++) {
				if (data[j][i].charAt(0) == vowels.charAt(k)) {
					return true;
				}
			}
		}
		return false;
	}
}
