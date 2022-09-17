public class Task03 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	int s = 0;
	int delta = 2;
	for (int i = 1; i <= n; i++) {
	    for (int j = 1; j < i; j++) {
		delta *= 2;
	    }
	    s += delta;
	    delta = 2;
	}
	System.out.println(s);
    }
}
