public class Task06 {
    public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);
	double y = Double.parseDouble(args[1]);
	double n = Double.parseDouble(args[2]);
	if (n * n - x * x - y * y >= 0.0001) {
	    System.out.println(x + " " + y + " are inside");
	}
	else {
	    System.out.println(x + " " + y + " aren't inside");
	}
    }
}
