import java.util.Iterator;

public class LinkedCollectionIterator<T> implements Iterator<T> {
    protected Elem<T> head;
    protected int size;
    protected Elem<T> point;
    public LinkedCollectionIterator(Elem<T> head, int size) {
        this.head = head;
        this.size = size;
        this.point = this.head;
    }

    @Override
    public boolean hasNext() {
        return this.point.getNext() != null;
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            Elem<T> temp = this.point;
            this.point = point.getNext();
            return temp.getValue();
        }
        return null;
    }
}
