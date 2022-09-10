public class Task07 {
    public static void main(String[] args) {
	double x = Double.parseDouble(args[0]);
	double y = Double.parseDouble(args[1]);
	int i = 1;
	boolean stat = false;
	while (i <= 10) {
	    if (i * i - x * x - y * y >= 0.00000001) {
		stat = true;
		break;
	    }
	    i++;
	}
	if (stat == true) {
	    System.out.println(i);
	}
	else {
	    System.out.println("Missed");
	}
    }
}
