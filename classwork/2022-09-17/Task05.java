public class Task05 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	double x = Double.parseDouble(args[1]);
	double s = 0;
	double delta1 = 1, delta2 = x;
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j <= i; j++) {
		delta1 *= j;
	    }
	    for (int j = 1; j < i; j++) {
		delta2 *= x;
	    }
	    s += (double) delta1 * delta2;
	    delta1 = 1;
	    delta2 = x;
	}
	System.out.println(s);
    }
}
