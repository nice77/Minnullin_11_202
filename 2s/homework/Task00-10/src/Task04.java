import java.util.Scanner;

public class Task04 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = reverseCreate(5);
        System.out.println(head);
    }
    public static Elem reverseCreate(int n) {
        Elem head = new Elem(sc.nextInt(), new Elem());
        Elem end = head, p;
        for (int i = 0; i < n; i++) {
            p = new Elem(i, end.getNext());
            end.setNext(p);
            end = p;
        }
        return head;
    }
}
