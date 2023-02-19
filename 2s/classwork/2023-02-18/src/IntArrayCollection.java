import java.util.Collection;
import java.util.Iterator;

public class IntArrayCollection extends AbstractCollection {
    private int capacity = 128;
    private final double LOAD_FACTOR = 0.75;
    private int size = 0;
    private Integer[] arr;
    public IntArrayCollection() {
        this.arr = new Integer[this.capacity];
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
        for (int i = 0; i < this.size; i++) {
            if (this.arr[i] == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return this.arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        this.arr = checkAndResize();
        this.arr[this.size] = integer;
        this.size += 1;
        return true;
    }

    private Integer[] checkAndResize() {
        if (this.size > this.capacity * LOAD_FACTOR) {
            Integer[] temp = new Integer[(int) (this.capacity + this.capacity * LOAD_FACTOR)];
            for (int i = 0; i < this.capacity; i++) {
                temp[i] = this.arr[i];
            }
            this.capacity = (int) (this.capacity + this.capacity * LOAD_FACTOR);
            return temp;
        }
        return this.arr;
    }

    @Override
    public boolean remove(Object o) {
        Integer[] temp = new Integer[this.capacity];
        int i = 0, j = 0;
        while (j != this.size) {
            if (this.arr[i] == o) {
                i += 1;
                this.size -= 1;
            }
            temp[j] = this.arr[i];
            i += 1;
            j += 1;
        }
        this.arr = temp;
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
