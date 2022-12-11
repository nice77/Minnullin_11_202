import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

class Main {
	public static void main(String[] args) throws FileNotFoundException{
		String[] films = createFilms();
		User[] users = new User[15];
		createUsers(users);
		User nice = new User("Neyas:+-+--+-+-+----+-------++---+-----+--+-+---++-+-+-++++-+++-+-+-+++++++-++-++++----++---++++------++++++----++---------+--++-+---+-++++++++--++++++---+++--++-");

		ComparedUser[] list = nice.comparePlusses(users);
		System.out.println("Sorted:");
		bubbleSort(list);
		String[] recommendedFilms = getRecommendations(nice, list, films);
		for (int i = 0; i < recommendedFilms.length; i++) {
			System.out.println(i + ": " + recommendedFilms[i]);
		}
	}

	public static String[] createFilms() throws FileNotFoundException{
		Scanner scFile = new Scanner(new File("text.txt"));
		String[] out = new String[0];
		do {
			out = append(out, scFile.nextLine());
		} while (scFile.hasNextLine());
		return out;
	}
	public static String[] append(String[] list, String line) {
		String[] out = new String[list.length + 1];
		for (int i = 0; i < list.length; i++) {
			out[i] = list[i];
		}
		out[list.length] = line;
		return out;
	}
	public static String[] getRecommendations(User user, ComparedUser[] list, String[] films) {
		String[] recommendedFilms = new String[0];
		final int threshold = 68;
		for (ComparedUser cUser : list) {
			if (cUser.getPercent() >= threshold) {
				for (int i = 0; i < films.length; i++) {
					if (cUser.getDataAt(i) == '+' && user.getDataAt(i) == '-') {
						if (!contains(recommendedFilms, films[i])) {
							recommendedFilms = append(recommendedFilms, films[i]);
						}
					}
				}
			}
		}
		return recommendedFilms;
	}
	public static boolean contains(String[] list, String line) {
		for (String elem : list) {
			if (line.equals(elem)) {
				return true;
			}
		}
		return false;
	}
	public static void createUsers(User[] users) throws FileNotFoundException {
		Scanner scFile = new Scanner(new File("data.txt"));
		String temp;
		int i = 0;
		do {
			temp = scFile.nextLine();
			if (temp.split(":")[0].equals("Neyas")) {
				continue;
			}
			users[i] = new User(temp);
			i++;
		} while (scFile.hasNextLine());
	}
	public static void bubbleSort(ComparedUser[] a) {
		ComparedUser temp;
		for (int i = 0; i < a.length - 1; i++) {
			for (int j = 0; j < a.length - 1 - i; j++) {
				if (a[j].getPercent() > a[j + 1].getPercent()) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		for (ComparedUser user : a) {
			System.out.println(user);
		}
	}
}
