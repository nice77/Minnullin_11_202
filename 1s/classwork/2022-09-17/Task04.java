public class Task04 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	int s = 0;
	int delta = 1;
	for (int i = 0; i < n; i++) {
	    for (int j = 0; j <= i; j++) {
		delta *= (j + 1);
	    }
	    s += delta;
	    delta = 1;
	}
	System.out.println(s);
    }
}
