import java.util.Scanner;

public class Task02 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        System.out.println(contains(head, 1337));
    }

    public static boolean contains(Elem pointer, int x) {
        if (pointer.getNext() != null) {
            if (pointer.getValue() == x) {
                return true;
            }
            return contains(pointer.getNext(), x);
        }
        return false;
    }
}
