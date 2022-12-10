public class Task10 {
    public static void main(String[] args) {
	int k = Integer.parseInt(args[0]);
	int s = 0;
	while (k != 0) {
	    s += k % 10;
	    k /= 10;
	}
	System.out.println(s);
    }
}
