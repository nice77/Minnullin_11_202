import java.util.Random;

public class Task00 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	Random r = new Random();
	int[] a = new int[n];
	for (int i = 0; i < n; i++) {
	    a[i] = r.nextInt();
	}
	int m = a[0];
	for (int i = 0; i < n; i++) {
	    System.out.print(a[i] + " ");
	    if (a[i] > m) {
		m = a[i];
	    }
	}
	System.out.println("\n" + m);
    }
}
