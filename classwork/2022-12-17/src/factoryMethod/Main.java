package factoryMethod;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input garage size -> ");
        int size = sc.nextInt();
        Transport[] garage = TransportCreator.garageCreator(size);
        System.out.println(Arrays.toString(garage));
    }
}
