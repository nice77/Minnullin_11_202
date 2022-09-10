public class Task05 {
    public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);
	double y = Double.parseDouble(args[1]);
	double n = Double.parseDouble(args[2]);
	if (n - x > 0.0001 && n - y > 0.0001) {
	    System.out.println(x + " " + y + " are inside");
	}
	else {
	    System.out.println(x + " " + y + " aren't inside");
	}
    }
}
