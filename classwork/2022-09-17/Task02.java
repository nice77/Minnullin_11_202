public class Task02 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	int s = 0;
	int delta = n;
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j < i; j++) {
		delta *= n;
	    }
	    s += delta;
	    delta = n;
	}
	System.out.println(s);
    }
}
