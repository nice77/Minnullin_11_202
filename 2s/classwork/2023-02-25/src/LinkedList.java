import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> extends LinkedCollection<T> implements List<T> {

    public LinkedList() {
        super();
    }

    public LinkedList(Elem<T> head, int fromIndex, int toIndex) {
        this.head = head;
        int i = 0;
        while (i != fromIndex) {
            this.head = this.head.getNext();
            i++;
        }
        this.size = toIndex - fromIndex;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public T get(int index) {
        int i = 0;
        Elem<T> p = this.head;
        while (p.getNext() != null && i < this.size) {
            if (i == index) {
                return p.getValue();
            }
            p = p.getNext();
            i++;
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        int i = 0;
        Elem<T> p = this.head;
        while (p.getNext() != null && i < this.size) {
            if (i == index) {
                T temp = p.getValue();
                p.setValue(element);
                return temp;
            }
            p = p.getNext();
            i++;
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        int i = 0;
        Elem<T> p = this.head;
        while (p.getNext() != null && i < this.size) {
            if (i + 1 == index) {
                Elem<T> temp = new Elem<>(element, p.getNext());
                p.setNext(temp);
                this.size += 1;
                return;
            }
            p = p.getNext();
            i++;
        }
    }

    @Override
    public T remove(int index) {
        int i = 0;
        if (i == index) {
            this.head = this.head.getNext();
            this.size -= 1;
        }
        Elem<T> p = this.head;
        while (p.getNext() != null && i < this.size) {
            if (i + 1 == index) {
                Elem<T> temp = p.getNext();
                if (p.getNext().getNext() != null) {
                    p.setNext(p.getNext().getNext());
                }
                else {
                    p.setNext(null);
                }
                this.size -= 1;
                return temp.getValue();
            }
            p = p.getNext();
            i++;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Elem<T> p = this.head;
        int i = 0;
        while (p.getNext() != null) {
            if (p.getValue().equals(o)) {
                return i;
            }
            p = p.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Elem<T> p = this.head;
        int i = 0, max = -1;
        while (p.getNext() != null) {
            if (p.getValue().equals(o)) {
                max = i;
            }
            p = p.getNext();
            i++;
        }
        return max;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return new LinkedList(this.head, fromIndex, toIndex);
    }
}
