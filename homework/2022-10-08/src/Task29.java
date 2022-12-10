import java.util.Random;

public class Task29 {
	public static void main(String[] args) {
		Random r = new Random();
		int[][] data = new int[r.nextInt(10)][r.nextInt(10)];
		System.out.println("Arr's size: " + data[0].length + " " + data.length);
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = r.nextInt(100);
				System.out.print(data[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println(checker(data));
	}

	public static boolean checker(int[][] arr) {
		for (int[] ints : arr) {
			if (divThr(ints)) {
				return true;
			}
		}
		return false;
	}

	public static boolean divThr(int[] arr) {
		for (int elem : arr) {
			if (elem % 3 != 0) {
				return false;
			}
		}
		return true;
	}
}
