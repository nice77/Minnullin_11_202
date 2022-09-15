public class Task13 {
    public static void main(String[] args) {
	int k = Integer.parseInt(args[0]);
	int n;
	int i;
	int out = 0;
	while (k != 0) {
	    i = 1;
	    n = 1;
	    while (n <= k) {
		n *= 2;
		i *= 10;
	    }
	    k -= (n / 2);
	    out += (i / 10);
	}
	System.out.println(out);
    }
}
