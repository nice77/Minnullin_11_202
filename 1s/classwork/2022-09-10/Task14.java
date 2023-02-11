import java.util.Scanner;


public class Task14 {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.print("Input a number: ");
	int a = sc.nextInt();
	while (a != 0) {
	    System.out.print("Input a number: ");
	    a = sc.nextInt();
	}
    }
}
