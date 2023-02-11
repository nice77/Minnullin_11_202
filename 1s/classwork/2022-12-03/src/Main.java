import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		Scanner scfile = new Scanner(new File("data.txt"));
		User[] users = new User[15];
		String[] names = new String[15];
		String temp;
		int i = 0;
		do {
			temp = scfile.nextLine();
			if (temp.split(":")[0].equals("Neyas")) {
				continue;
			}
			users[i] = new User(temp);
			names[i] = temp.split(":")[0];
			i++;
		}
		while (scfile.hasNextLine());
		User nice = new User("Neyas:+-+--+-+-+----+-------++---+-----+--+-+---++-+-+-++++-+++-+-+-+++++++-++-++++----++---++++------++++++----++---------+--++-+---+-++++++++--++++++---+++--++-");
		int[] a = nice.comparePlusses(users);
		for(int j = 0; j < 15; j++) {
			System.out.println(names[j] + " -> " + a[j]);
		}
		System.out.println("----------");
		bubbleSort(a, names);
	}

	public static void bubbleSort(int[] a, String[] names) {
		int temp;
		String name;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
					name = names[j];
					names[j] = names[j + 1];
					names[j + 1] = name;
				}
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.println(names[i] + " -> " + a[i]);
		}
	}
}
