import java.util.Scanner;

public class Task25 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String data = sc.nextLine();
		System.out.println(allVowels(data));
	}

	public static boolean allVowels(String data) {
		for (int i = 0; i < data.length(); i++) {
			if (!elemIn(data.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean elemIn(char elem) {
		String vowels = "aeiouyAEIOUY";
		for (int j = 0; j < vowels.length(); j++) {
			if (vowels.charAt(j) == elem) {
				return true;
			}
		}
		return false;
	}
}
