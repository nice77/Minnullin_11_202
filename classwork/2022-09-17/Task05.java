public class Task05 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	double x = Double.parseDouble(args[1]);
	double s = 0;
	double delta1 = 1, delta2 = x;
	for (int i = 1; i <= n; i++) {
	    delta1 *= i;
	    s += (double) delta1 * delta2;
	    delta2 *= x;
	}
	System.out.println(s);
    }
}
