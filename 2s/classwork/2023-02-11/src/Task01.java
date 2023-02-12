import java.util.Scanner;

public class Task01 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        System.out.println(findMax(head).getValue());
    }
    public static Elem findMax(Elem base) {
        Elem pointer = base.getNext();
        Elem maximum = base;
        while (pointer.getNext() != null) {
            if (pointer.getValue() > maximum.getValue()) {
                maximum = pointer;
            }
            pointer = pointer.getNext();
        }
        return maximum;
    }

}
