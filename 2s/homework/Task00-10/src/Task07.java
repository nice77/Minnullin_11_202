import java.util.Scanner;

public class Task07 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        System.out.println(deleteEquals(head, sc.nextInt()));
    }

    public static Elem deleteEquals(Elem head, int x) {
        while (head != null && head.getValue() == x) {
            head = head.getNext();
        }
        if (head == null) {
            return head;
        }
        Elem pointer = head;
        while (pointer.getNext() != null) {
            if (pointer.getNext().getValue() == x) {
                if (pointer.getNext().getNext() != null) {
                    pointer.setNext(pointer.getNext().getNext());
                }
                else {
                    pointer.setNext(null);
                }
            }
            else {
                pointer = pointer.getNext();
            }
        }
        return head;
    }
}
