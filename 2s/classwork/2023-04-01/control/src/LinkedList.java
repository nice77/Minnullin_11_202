import java.util.Arrays;

public class LinkedList {
    private Elem head;

    public LinkedList() {
        this.head = null;
    }

    public void add(int[] value) {
        Elem p = new Elem(value);
        p.setNext(this.head);
        this.head = p;
    }

    public String toString() {
        Elem pointer = this.head;
        String out = "";
        while (pointer != null) {
            out += Arrays.toString(pointer.getValue());
            pointer = pointer.getNext();
        }
        return out;
    }

    public Elem getHead() {
        return this.head;
    }
}
