import java.util.Scanner;

public class Task03 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Elem head = Task00.create(sc.nextInt());
        sortElem(head);
        System.out.println(head);
    }

    public static void sortElem(Elem head) {
        int temp;
//        for (int i = 0; i < head.getSize(); i++) {
//            for (int j = 0; j < head.getSize() - i; j++) {
//                if (head.get(j).getValue() > head.get(j + 1).getValue()) {
//                    temp = head.get(j).getValue();
//                    head.get(j).setValue(head.get(j + 1).getValue());
//                    head.get(j + 1).setValue(temp);
//                }
//            }
//        }
        Elem pointerI = head, pointerJ;
        int i = 0, j;
        while (pointerI.getNext() != null) {
            j = 0;
            pointerJ = head;
            while (j < head.getSize() - i - 1) {
                if (pointerJ.getValue() > pointerJ.getNext().getValue()) {
                    temp = pointerJ.getValue();
                    pointerJ.setValue(pointerJ.getNext().getValue());
                    pointerJ.getNext().setValue(temp);
                }
                pointerJ = pointerJ.getNext();
                j++;
            }
            pointerI = pointerI.getNext();
            i++;
        }
    }
}
