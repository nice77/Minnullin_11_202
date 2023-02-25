import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class LinkedList<T> extends LinkedCollection<T> implements List<T> {
    public LinkedList() {
        super();
    }
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int i = index;
        for (T x : c) {
            this.add(i, x);
            i++;
        }
        return true;
    }

    @Override
    public T get(int index) {
        Elem<T> pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i == index) {
                return pointer.getValue();
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    @Override
    public T set(int index, T element) {
        Elem<T> pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i == index) {
                T temp = pointer.getValue();
                pointer.setValue(element);
                return temp;
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        Elem<T> pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i + 1 == index) {
                Elem<T> toAdd = new Elem<T>(element, pointer.getNext());
                pointer.setNext(toAdd);
                return;
            }
            pointer = pointer.getNext();
            i++;
        }
    }

    @Override
    public T remove(int index) {
        Elem<T> pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i + 1 == index) {
                T temp = pointer.getNext().getValue();
                if (pointer.getNext().getNext() != null) {
                    pointer.setNext(pointer.getNext().getNext());
                }
                else {
                    pointer.setNext(null);
                }
                return temp;
            }
            pointer = pointer.getNext();
            i++;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Elem<T> pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (pointer.getValue().equals(o)) {
                return i;
            }
            pointer = pointer.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Elem<T> pointer = this.head;
        int i = 0, max = 0;
        while (pointer.getNext() != null) {
            if (pointer.getValue().equals(o)) {
                max = i;
            }
            pointer = pointer.getNext();
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
        int i = 0;
        Elem<T> pointer = this.head;
        T[] temp = (T[]) new Object[toIndex - fromIndex];
        while (pointer.getNext() != null) {
            if (i >= fromIndex && i < toIndex) {
                temp[i - fromIndex] = pointer.getValue();
            }
            i++;
            pointer = pointer.getNext();
        }
        List<T> out = new LinkedList<>();
        for (int j = toIndex - fromIndex; j > 0; j--) {
            out.add(temp[i]);
        }
        return out;
    }
}
