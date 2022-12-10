public class Task19 {
    public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int s = 0;
		int delta = 1;
		for (int i = 1; i <= n; i++) {
			delta *= i;
			s += delta;
		}
		System.out.println(s);
	}
}
