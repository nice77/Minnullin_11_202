public class Task06 {
    public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);

	final double EPS = 1e-10;
	double k = 1;
	double a = 1;
	double result = 0.0;
	result += a;
	// check if our "delta" digit is less than the EPS
	while (Math.abs(a) > EPS) {
	    a = (a * x) / k;
	    result += a;
	    k++;
	}
	System.out.println(result);
    }
}
