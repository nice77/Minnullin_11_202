import java.util.Scanner;

public class Task06 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int x = sc.nextInt();
        Elem head = Task00.create(sc.nextInt());
        System.out.println(deleteFirstRem(head, x));
    }

    public static Elem deleteFirstRem(Elem head, int x) {
        if (head.getValue() == x) {
            return head.getNext();
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
                return head;
            }
            pointer = pointer.getNext();
        }
        return head;
    }
}
