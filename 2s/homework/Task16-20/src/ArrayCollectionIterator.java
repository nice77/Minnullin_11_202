import java.util.Iterator;

public class ArrayCollectionIterator<T> implements Iterator<T> {
    protected int pointer;
    protected T[] arr;
    protected int size;

    public ArrayCollectionIterator(T[] arr, int size) {
        this.pointer = 0;
        this.size = size;
        this.arr = arr;
    }

    @Override
    public boolean hasNext() {
        return this.pointer < this.size;
    }

    @Override
    public T next() {
        if (this.hasNext()) {
            T temp = this.arr[this.pointer];
            this.pointer += 1;
            return temp;
        }
        return null;
    }
}
