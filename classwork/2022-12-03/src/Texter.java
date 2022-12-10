import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Texter {
	public static void main(String[] args) throws FileNotFoundException {
		String out = "";
		Scanner scfile = new Scanner(new File("text.txt"));
		Scanner sc = new Scanner(System.in);
		do {
			System.out.print(scfile.nextLine() + ": ");
			out += sc.nextLine();
		}
		while (scfile.hasNextLine());
		scfile.close();
		for (int i = 0; i < out.length(); i++) {
			System.out.println(out.charAt(i));
		}
	}
}
