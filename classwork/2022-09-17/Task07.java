public class Task07 {
    public static void main(String[] args) {
	double n = Double.parseDouble(args[0]);
	final double EPS = 1e-10;
	double k = 1.0;
	double res = 0.0;
	double delta = 1.0;
	double check;
	while (k > EPS) {
	    for (int i = -1; i < 10; i++) {
		check = res + (i + 1) * k;
		if (check * check > n){
		    res += i * k;
		    break;
		}
	    }
	    k /= 10;
	}
	System.out.println(res);
    }
}
