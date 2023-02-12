import java.util.Scanner;

public class Task09 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        placeBefore(head, sc.nextInt(), sc.nextInt());
        System.out.println(head);
    }

    public static void placeBefore(Elem head, int x, int y) {
        Elem pointer = head;
        while (pointer.getNext() != null) {
            if (pointer.getValue() == x) {
                pointer.setValue(y);
                Task08.placeAfter(head, y, x);
                break;
            }
            pointer = pointer.getNext();
        }
        if (pointer.getNext() == null && pointer.getValue() == x) {
            pointer.setValue(y);
            Task08.placeAfter(head, y, x);
        }
    }
}
