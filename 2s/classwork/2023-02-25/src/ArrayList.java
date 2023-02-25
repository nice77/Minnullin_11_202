import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<T> extends ArrayCollection<T> implements List<T> {

    public ArrayList() {
        super();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int i = index;
        for (T x : c) {
            this.add(i, x);
            i++;
        }
        return false;
    }

    @Override
    public T get(int index) {
        return this.arr[index];
    }

    @Override
    public T set(int index, T element) {
        T temp = this.arr[index];
        this.arr[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        this.arr = this.checkAndResize();
        T[] temp = (T[]) new Object[this.capacity];
        for (int i = 0; i < index; i++) {
            temp[i] = this.arr[i];
        }
        temp[index] = element;
        for (int i = index + 1; i < this.capacity + 1; i++) {
            temp[i] = this.arr[i];
        }
        this.arr = temp;
        this.size++;
    }

    @Override
    public T remove(int index) {
        T tempValue = null;
        if (index < this.size) {
            T[] temp = (T[]) new Object[this.capacity];
            int j = 0;
            for (int i = 0; i < this.size; i++) {
                if (i != index) {
                    temp[j] = this.arr[i];
                    j++;
                } else {
                    tempValue = this.arr[i];
                }
            }
            this.size -= 1;
        }
        return tempValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 1; i >= 0; i--) {
            if (this.arr[i].equals(o)) {
                return i;
            }
        }
        return -1;
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
        T[] temp = (T[]) new Object[toIndex - fromIndex];
        for (int i = fromIndex; i < toIndex; i++) {
            temp[i - fromIndex] = this.arr[i];
        }
        List<T> out = new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            out.add(temp[i]);
        }
        return out;
    }
}
