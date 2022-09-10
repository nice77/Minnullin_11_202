public class Task08 {
    public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);
	double y = Double.parseDouble(args[1]);
	boolean one = (16 - x * x - y * y) >= 0.0001;
	boolean two = 1 - x * x - (y - 2) * (y - 2) >= 0.001;
	boolean three = (4 - x * x - (y + 2) * (y + 2) >= 0.001) && (1 - x * x - (y + 2) * (y + 2) <= 0.001);
	if (one && (two || three)) {
	    System.out.println(x + " " + y + " are inside");
	}
	else {
	    System.out.println(x + " " + y + " aren't inside");
	}
    }
}
