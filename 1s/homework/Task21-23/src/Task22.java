import java.util.Scanner;
public class Task22 {
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int[] dataUno = new int[size];
		size  = sc.nextInt();
		int[] dataDos = new int[size];
		for (int i = 0; i < size; i++) {
			dataUno[i] = sc.nextInt();
			dataDos[i] = sc.nextInt();
		}
		System.out.println(equal(dataUno, dataDos));
	}

	public static boolean equal(int[] uno, int[] dos) {
		if (uno.length != dos.length) {
			return false;
		}
		for (int i = 0; i < uno.length; i++) {
			if (uno[i] != dos[i]) {
				return false;
			}
		}
		return true;
	}
}
