import java.util.Scanner;

public class Task05 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        Task03.sortElem(head);
        System.out.println(deleteTwo(head));
    }
    public static Elem deleteTwo(Elem head) {
        for (int i = 0; i < 2; i++) {
            if (head != null) {
                head = head.getNext();
            }
        }
        return head;
    }
}
