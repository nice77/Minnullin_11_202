public class Task18 {
    public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int s = 0;
		int delta = 2;
		for (int i = 1; i <= n; i++) {
			s += delta;
			delta *= 2;
		}
		System.out.println(s);
	}
}
