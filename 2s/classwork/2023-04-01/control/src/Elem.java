public class Elem {
    private int[] value;
    private Elem next;

    public Elem(int[] value, Elem next) {
        this.value = value;
        this.next = next;
    }

    public Elem(int[] value) {
        this(value, null);
    }

    public Elem() {
        this(null, null);
    }

    public int[] getValue() {
        return this.value;
    }

    public Elem getNext() {
        return this.next;
    }

    public void setValue(int[] n) {
        this.value = n;
    }

    public void setNext(Elem next) {
        this.next = next;
    }
}
