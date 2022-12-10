public class Task20 {
    public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		double x = Double.parseDouble(args[1]);
		double s = 0;
		double k = 1, delta2 = x;
		for (int i = 1; i <= n; i++) {
			k *= i;
			s += k * delta2;
			delta2 *= x;
		}
		System.out.println(s);
	}
}
