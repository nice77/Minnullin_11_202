public class Task11 {
    public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		int out = 0;
		while (k != 0) {
			out += (k % 10);
			out *= 10;
			k /= 10;
		}
		out /= 10;
		System.out.println(out);
	}
}
