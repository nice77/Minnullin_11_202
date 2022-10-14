import java.util.Scanner;

public class Task04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		System.out.println(threeVowels(data));
	}

	public static boolean threeVowels(String data) {
		int n = 0;
		for (int i = 0; i < data.length(); i++) {
			if (elemIn(data.charAt(i))) {
				n++;
			}
			if (n == 4) {
				return false;
			}
		}
		return ((n != 3) ? false : true);
	}

	public static boolean elemIn(char elem) {
		String vowels = "aeiouyAEIOUY";
		for (int i = 0; i < vowels.length(); i++) {
			if (vowels.charAt(i) == elem) {
				return true;
			}
		}
		return false;
	}
}
