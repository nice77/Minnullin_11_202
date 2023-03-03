import java.util.Collection;
import java.util.Iterator;

public class LinkedCollection<T> extends AbstractCollection<T> {
    protected Elem<T> head;
    public LinkedCollection() {
        this.head = null;
    }

    @Override
    public boolean contains(Object o) {
        Elem<T> p = this.head;
        while (p.getNext() != null) {
            if (p.getValue() == o) {
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedCollectionIterator<>(this.head, this.size);
    }

    @Override
    public Object[] toArray() {
        T[] out = (T[]) new Object[this.size];
        Elem<T> p = head;
        for (int i = 0; i < this.size; i++) {
            out[i] = p.getValue();
            p = p.getNext();
        }
        return out;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    public boolean add(T integer) {
        Elem<T> p = new Elem<>(integer, this.head);
        this.head = p;
        this.size += 1;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (this.head == null) {
            return true;
        }
        if (this.head.getValue() == o) {
            this.head = this.head.getNext();
            this.size -= 1;
            return true;
        }
        Elem p = this.head;
        while (p.getNext() != null) {
            if (p.getNext().getValue() == o) {
                if (p.getNext().getNext() != null) {
                    p.setNext(p.getNext().getNext());
                }
                else {
                    p.setNext(null);
                }
                this.size -= 1;
                return true;
            }
            p = p.getNext();
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Object x : c) {
            if (!this.contains(x)) {
                this.remove(x);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }
}
