import java.util.ArrayList;
import java.util.List;

public class SubList<T> extends ArrayList<T> {
    private final List<T> reference;
    private final int fromIndex, size;

    public SubList(List<T> reference, int fromIndex, int toIndex) {
        this.reference = reference;
        this.fromIndex = fromIndex;
        this.size = toIndex - fromIndex;
    }

    public int size() {
        return this.size;
    }

    public T get(int index) {
        return this.checkIndex(index) ? this.reference.get(this.fromIndex + index) : null;
    }

    public T set(int index, T elem) {
        if (!this.checkIndex(index)) {
            return null;
        }
        T temp = this.get(index);
        this.reference.set(this.fromIndex + index, elem);
        return temp;
    }

    private boolean checkIndex(int index) {
        return index < this.fromIndex + this.size;
    }
}
