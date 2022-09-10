import java.util.Scanner;

public class Task03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	float uno = sc.nextFloat();
	float dos = sc.nextFloat();
	float tres = sc.nextFloat();
	float max = uno;
	if (max < dos) {
	    max = dos;
	    if (tres > max) {
		max = tres;
	    }
	}
	System.out.println(max);
    }
}
