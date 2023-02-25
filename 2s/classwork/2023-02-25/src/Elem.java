public class Elem<T> {
    private T value;
    private Elem<T> next;

    public Elem(T value, Elem<T> next) {
        this.value = value;
        this.next = next;
    }

    public Elem<T> getNext() {
        return this.next;
    }
    public T getValue() {
        return this.value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setNext(Elem<T> next) {
        this.next = next;
    }
}
