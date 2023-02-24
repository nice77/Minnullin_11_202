import java.util.Scanner;

public class Task00 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = create(sc.nextInt());
        System.out.println(head);
    }
    public static Elem create(int n) {
        Elem out = null, p;
        for (int i = 0; i < n; i++) {
            p = new Elem(sc.nextInt(), out);
            out = p;
        }
        return out;
    }
}
