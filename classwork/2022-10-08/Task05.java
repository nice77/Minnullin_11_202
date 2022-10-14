import java.util.Random;

public class Task05 {
	public static void main(String[] args) {
		Random r = new Random();
		/*int[] data = new int[r.nextInt(20)];
		for (int i = 0; i < data.length; i++) {
			data[i] = r.nextInt();
			System.out.print(data[i] + " ");
		}*/
		int[] data = {10};//, 20, 10, 20, 31, -100, 200};
		System.out.println(checker(data));
	}

	public static boolean checker(int[] arr) {
		for (int elem : arr) {
			if (elem > 0 && !odd(elem)) {
				return false;
			}
		}
		return true;
	}

	public static boolean odd(int intgr) {
		while (intgr != 0) {
			if ((intgr % 10) % 2 == 0) {
				return true;
			}
			intgr /= 10;
		}
		return false;
	}
}
