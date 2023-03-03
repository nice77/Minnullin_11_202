import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<T> extends ArrayCollection<T> implements List<T> {
    private final int fromIndex;
    private T[] arr;

    public ArrayList() {
        super();
        this.fromIndex = 0;
    }

    public ArrayList(T[] arr, int fromIndex, int toIndex) {
        this.arr = arr;
        this.fromIndex = fromIndex;
        this.size = toIndex - fromIndex;
    }

    public T get(int index) {
        return this.checkIndex(index) ? this.arr[index + this.fromIndex] : null;
    }

    public T set(int index, T elem) {
        if (this.checkIndex(index)) {
            T temp = this.arr[index + this.fromIndex];
            this.arr[index + this.fromIndex] = elem;
            return temp;
        }
        return null;
    }

    public boolean contains(Object o) {
        for (int i = this.fromIndex; i < this.size + this.fromIndex; i++) {
            if (this.arr[i] == o) {
                return true;
            }
        }
        return false;
    }

    private void leftOnOne(int place) {
        for (int i = place; i < this.size + this.fromIndex; i++) {
            this.arr[i] = this.arr[i + 1];
        }
    }

    public boolean remove(Object o) {
        if (this.contains(o)) {
            int place = this.size + this.fromIndex;
            for (int i = this.fromIndex; i < this.size + this.fromIndex; i++) {
                if (this.arr.equals(o)) {
                    place = i;
                    this.arr[i] = null;
                    this.size -= 1;
                    break;
                }
            }
            leftOnOne(place);
        }
        return true;
    }

    public T remove(int index) {
        if (this.checkIndex(index)) {
            T elem = this.arr[index + this.fromIndex];
            this.size -= 1;
            leftOnOne(index + this.fromIndex);
            return elem;
        }
        return null;
    }

    private void rightOnOne(int place) {
        for (int i = this.size + this.fromIndex; i > place; i--) {
            this.arr[i] = this.arr[i - 1];
        }
    }

    public boolean add(T obj) {
        this.arr = checkAndResize();
        this.add(0, obj);
        this.size += 1;
        return true;
    }

    public void add(int index, T element) {
        this.arr = this.checkAndResize();
        rightOnOne(index + this.fromIndex);
        this.arr[index + this.fromIndex] = element;
        this.size += 1;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        int i = index + this.fromIndex;
        for (T x : c) {
            this.add(i, x);
            i++;
        }
        return true;
    }

    public int indexOf(Object o) {
        for (int i = this.fromIndex; i < this.size + this.fromIndex; i++) {
            if (this.arr[i].equals(o)) {
                return i - this.fromIndex;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for (int i = this.fromIndex + this.size - 1; i >= this.fromIndex; i--) {
            if (this.arr[i].equals(o)) {
                return i - this.fromIndex;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new ArrayListIterator<>(this.arr, this.size, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return new ArrayList<>(this.arr, fromIndex, toIndex);
    }

    public Object[] toArray() {
        T[] out = (T[]) new Object[this.size];
        for (int i = this.fromIndex; i < this.size + this.fromIndex; i++) {
            out[i - this.fromIndex] = this.arr[i];
        }
        return out;
    }

    private boolean checkIndex(int index) {
        return index < this.fromIndex + this.size;
    }

    public void clear() {
        for (int i = this.fromIndex; i < this.size + this.fromIndex; i++) {
            this.arr[i] = null;
        }
        this.size = 0;
    }
}
