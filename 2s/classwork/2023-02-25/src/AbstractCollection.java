import java.util.Collection;

abstract public class AbstractCollection<T> implements Collection<T> {
    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (Object o : c.toArray()) {
            this.add((T) o);
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
}
