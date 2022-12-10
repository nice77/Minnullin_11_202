import java.util.Random;

public class Task33 {
	public static void main(String[] args) {
		Random r = new Random();
		int[][] arr = new int[r.nextInt(10)][r.nextInt(10)];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				arr[i][j] = r.nextInt(100);
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println(checker(arr));
	}

	public static boolean checker(int[][] arr) {
		for (int[] row : arr) {
			if (!oddRowChecker(row)) {
				return false;
			}
		}
		return true;
	}

	public static boolean oddRowChecker(int[] row) {
		int c = 0;
		for (int elem : row) {
			if (oddChecker(elem)) {
				c++;
			}
			if (c >= 3) {
				return true;
			}
		}
		return false;
	}

	public static boolean oddChecker(int elem) {
		int delta;
		while (elem != 0) {
			delta = elem % 10;
			if (delta % 2 != 0) {
				return false;
			}
			elem /= 10;
		}
		return true;
	}

}
