import java.util.Scanner;
import java.util.Random;

public class Task01 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		int n = sc.nextInt();
		int[] data = new int[n];
		System.out.print("Array: ");
		for (int i = 0; i < n; i++) {
			data[i] = r.nextInt();
			System.out.print(data[i] + " ");
		}
		System.out.println("\n" + lowerThanZero(data));
	}

	public static boolean lowerThanZero(int[] arr) {
		for (int i : arr) {
			if (i < 0) {
				return true;
			}
		}
		return false;
	}
}
