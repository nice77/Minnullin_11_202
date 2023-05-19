package classwork.task02;

import java.util.Collection;
import java.util.Iterator;

public class IntArrayCollection extends AbstractCollection {
    protected int capacity = 128;
    protected static final double LOAD_FACTOR = 0.75;
    protected int size = 0;
    protected Integer[] arr;
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
        Object[] out = new Integer[this.size];
        for (int i = 0; i < this.size; i++) {
            out[i] = this.arr[i];
        }
        return out;
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

    protected Integer[] checkAndResize() {
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
        if (this.contains(o)) {
            Integer[] temp = new Integer[this.capacity];
            int j = 0;
            boolean found = false;
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == o && !found) {
                    found = true;
                }
                else {
                    temp[j] = this.arr[i];
                    j++;
                }
            }
            this.arr = temp;
            this.size -= 1;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for (Integer x : this.arr) {
            if (!c.contains(x)) {
                this.remove(x);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.arr[i] = null;
        }
        this.size = 0;
    }
}
