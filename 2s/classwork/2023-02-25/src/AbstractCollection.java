import java.util.Collection;

abstract public class AbstractCollection<T> implements Collection<T> {
    protected int size = 0;
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Object x : c.toArray()) {
            this.add((T) x);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object x : c) {
            if (!this.contains(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object x : c.toArray()) {
            this.remove(x);
        }
        return true;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
