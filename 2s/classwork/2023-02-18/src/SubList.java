import java.util.List;

public class SubList extends IntArrayList {
    private final List<Integer> reference;
    private final int fromIndex, size;

    public SubList(List<Integer> reference, int fromIndex, int toIndex) {
        this.reference = reference;
        this.fromIndex = fromIndex;
        this.size = toIndex - fromIndex;
    }

    public int size() {
        return this.size;
    }

    public Integer get(int index) {
        return this.checkIndex(index) ? this.reference.get(this.fromIndex + index) : null;
    }

    public Integer set(int index, Integer elem) {
        if (!this.checkIndex(index)) {
            return null;
        }
        Integer temp = this.get(index);
        this.reference.set(this.fromIndex + index, elem);
        return temp;
    }

    private boolean checkIndex(int index) {
        return index < this.fromIndex + this.size;
    }
}
