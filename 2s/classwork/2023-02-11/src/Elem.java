public class Elem {
    private int value;
    private Elem next;
    public Elem(int value, Elem next) {
        this.value = value;
        this.next = next;
    }
    public Elem(int value) {
        this(value, null);
    }
    public Elem() {
        this(0, null);
    }

    public int getSize() {
        int size = 1;
        Elem pointer = this;
        while (pointer.getNext() != null) {
            size++;
            pointer = pointer.getNext();
        }
        return size;
    }

    public Elem get(int n) {
        int i = 1;
        Elem pointer = this;
        while (i != n) {
            if (pointer.getNext() != null) {
                pointer = pointer.getNext();
            }
            else {
                return new Elem();
            }
            i++;
        }
        return pointer;
    }

    public Elem getNext() {
        return this.next;
    }

    public void setNext(Elem next) {
        this.next = next;
    }
    public int getValue() {
        return this.value;
    }
    public void setValue(int n) {
        this.value = n;
    }
    public String toString() {
        if (this.next == null) {
            return this.value + "";
        }
        return this.value + " " + this.next.toString();
    }
}
