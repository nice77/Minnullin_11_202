import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Texter {
	public static void main(String[] args) throws FileNotFoundException {
		String out = "";
		Scanner scfile = new Scanner(new File("mus.txt"));
		Scanner sc = new Scanner(System.in);
		int c = 1;
		do {
			System.out.print(c + "\\289 -> " + scfile.nextLine() + ": ");
			out += sc.nextLine();
			c++;
		}
		while (scfile.hasNextLine());
		scfile.close();
		for (int i = 0; i < out.length(); i++) {
			System.out.println(out.charAt(i));
		}
		System.out.println(out);
	}
}
