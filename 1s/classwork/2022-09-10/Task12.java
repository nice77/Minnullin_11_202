public class Task12 {
    public static void main(String[] args) {
	int k = Integer.parseInt(args[0]);
	int s = 0;
	int i = 1;
	while (k != 0) {
	    s += (k % 10) * i;
	    i *= 2;
	    k /= 10;
	}
	System.out.println(s);
    }
}
