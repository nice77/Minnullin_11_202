public class Task11 {
    public static void main(String[] args) {
	int k = Integer.parseInt(args[0]);
	while (k != 0) {
	    System.out.print(k % 10);
	    k /= 10;
	}
	System.out.println();
    }
}
