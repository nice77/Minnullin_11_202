import java.util.Scanner;

public class Task08 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        placeAfter(head, sc.nextInt(), sc.nextInt());
        System.out.println(head);
    }

    public static void placeAfter(Elem head, int x, int y) {
        Elem pointer = head;
        while (pointer.getNext() != null) {
            if (pointer.getValue() == x) {
                Elem p = new Elem(y, pointer.getNext());
                pointer.setNext(p);
                break;
            }
            pointer = pointer.getNext();
        }
        if (pointer.getNext() == null && pointer.getValue() == x) {
            Elem p = new Elem(y, null);
            pointer.setNext(p);
        }
    }
}
