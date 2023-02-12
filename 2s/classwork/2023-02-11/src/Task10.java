import java.util.Scanner;

public class Task10 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        placeEveryAfter(head, sc.nextInt(), sc.nextInt());
        System.out.println(head);
    }
    public static void placeEveryAfter(Elem head, int x, int y) {
        Elem pointer = head;
        while (pointer.getNext() != null) {
            if (pointer.getValue() == x) {
                Elem p = new Elem(y, pointer.getNext());
                pointer.setNext(p);
            }
            pointer = pointer.getNext();
        }
        if (pointer.getNext() == null && pointer.getValue() == x) {
            Elem p = new Elem(y, null);
            pointer.setNext(p);
        }
    }
}
