public class Task04 {
    public static void main(String[] args) {
	double uno = Double.parseDouble(args[0]);
	double dos = Double.parseDouble(args[1]);
	double max = uno;
	double min = dos;
	if (max < min) {
	    double c = max;
	    max = min;
	    min = c;
	}
	if (max - min < 0.0001) {
	    System.out.println("Numbers are equal");
	}
	else {
	    System.out.println("Numbers aren't equal");
	}
    }
}
