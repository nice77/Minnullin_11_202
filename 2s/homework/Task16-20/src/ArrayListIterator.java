import java.util.ListIterator;

public class ArrayListIterator<T> extends ArrayCollectionIterator<T> implements ListIterator<T> {
    private static double LOAD_FACTOR = 0.75;

    public ArrayListIterator(T[] arr, int size, int startPos) {
        super(arr, size);
        this.pointer = startPos;
    }

    @Override
    public boolean hasPrevious() {
        return this.pointer > 0;
    }

    @Override
    public T previous() {
        if (this.hasPrevious()) {
            T temp = this.arr[this.pointer];
            this.pointer--;
            return temp;
        }
        return null;
    }

    @Override
    public int nextIndex() {
        return this.pointer < size ? this.pointer + 1 : this.pointer;
    }

    @Override
    public int previousIndex() {
        return this.pointer > 0 ? this.pointer - 1 : this.pointer;
    }

    @Override
    public void remove() {
        T[] out = (T[]) new Object[this.arr.length];
        for (int i = 0; i < this.pointer; i++) {
            out[i] = this.arr[i];
        }
        for (int i = this.pointer; i < this.size - 1; i++) {
            out[i] = this.arr[i + 1];
        }
        this.arr = out;
        this.size--;
    }

    @Override
    public void set(T t) {
        if (this.hasPrevious()) {
            this.arr[this.pointer - 1] = t;
        }
    }

    @Override
    public void add(T t) {
        this.arr = checkAndResize();
        T[] out = (T[]) new Object[this.arr.length];
        for (int i = 0; i < this.pointer; i++) {
            out[i] = this.arr[i];
        }
        out[this.pointer] = t;
        for (int i = this.pointer + 1; i < this.size; i++) {
            out[i] = this.arr[i - 1];
        }
        this.size++;
    }

    protected T[] checkAndResize() {
        if (this.size > this.arr.length * LOAD_FACTOR) {
            T[] temp = (T[]) new Object[(int) (this.arr.length * (LOAD_FACTOR + 1))];
            for (int i = 0; i < this.arr.length; i++) {
                temp[i] = this.arr[i];
            }
            return temp;
        }
        return this.arr;
    }
}
