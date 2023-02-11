import java.util.Scanner;
public class Task01 {
    public static void main(String[] args) {
	int n = Integer.parseInt(args[0]);
	Scanner sc = new Scanner(System.in);
	int[] dataUno = new int[n];
	int[] dataDos = new int[n];
	for (int i = 0; i < n; i++) {
	    dataUno[i] = sc.nextInt();
	    dataDos[i] = sc.nextInt();
	}
	for (int i = 0; i < n; i++) {
	    if (dataUno[i] != dataDos[i]) {
		n = -1;
		break;
	    }
	}
	System.out.println(((n == -1) ? "Arrays aren't equal" : "Arrays are equal"));
    }
}
