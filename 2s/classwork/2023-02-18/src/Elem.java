public class Elem {
    private Integer value;
    private Elem next;

    public Elem(Integer value, Elem next) {
        this.value = value;
        this.next = next;
    }

    public Elem getNext() {
        return this.next;
    }
    public Integer getValue() {
        return this.value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public void setNext(Elem next) {
        this.next = next;
    }
}
