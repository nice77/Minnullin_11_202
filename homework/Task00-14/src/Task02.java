import java.util.Scanner;

public class Task02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	float a = sc.nextFloat();
	float b = sc.nextFloat();
	if (a > b) {
	    System.out.println("First number larger");
	}
	else if (b > a) {
	    System.out.println("Second number larger");
	}
	else {
	    System.out.println("Numbers are equal");
	}
    }
}
