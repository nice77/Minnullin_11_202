import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IntArrayList extends IntArrayCollection implements List<Integer> {

    public IntArrayList() {
        super();
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        int i = index;
        for (Integer x : c) {
            this.add(i, x);
            i++;
        }
        return false;
    }

    @Override
    public Integer get(int index) {
        return this.arr[index];
    }

    @Override
    public Integer set(int index, Integer element) {
        Integer temp = this.arr[index];
        this.arr[index] = element;
        return temp;
    }

    @Override
    public void add(int index, Integer element) {
        this.arr = this.checkAndResize();
        Integer[] temp = new Integer[this.capacity];
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
    public Integer remove(int index) {
        Integer tempValue = null;
        if (index < this.size) {
            Integer[] temp = new Integer[this.capacity];
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
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }
}
