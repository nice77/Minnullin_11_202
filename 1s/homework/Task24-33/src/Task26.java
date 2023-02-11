import java.util.Random;

public class Task26 {
	public static void main(String[] args) {
		Random r = new Random();
		int n = r.nextInt(10);
		int[] arr = new int[n];
		System.out.print("Array: ");
		for (int i = 0; i < n; i++) {
			arr[i] = r.nextInt();
			System.out.print(arr[i] + " ");
		}
		System.out.println("\n" + subZeroTwice(arr));
	}

	public static boolean subZeroTwice(int[] arr) {
		int num = 0;
		for (int elem : arr) {
			num = ((elem < 0) ? num + 1 : num);
			if (num >= 2) {
				return true;
			}
		}
		return false;
	}
}
