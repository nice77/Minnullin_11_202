import java.util.Collection;
import java.util.Iterator;

public class IntLinkedCollection extends AbstractCollection {
    protected Elem head;
    protected int size;
    public IntLinkedCollection () {
        this.head = null;
        this.size = 0;
    }

    public Elem getHead() {
        return this.head;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {
        Elem p = this.head;
        while (p.getNext() != null) {
            if (p.getValue() == o) {
                return true;
            }
            p = p.getNext();
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Integer[] out = new Integer[this.size];
        Elem p = head;
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

    public boolean add(Integer integer) {
        Elem p = new Elem(integer, this.head);
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
        return false;
    }

    @Override
    public void clear() {

    }
}
