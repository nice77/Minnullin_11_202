import java.util.Collection;

abstract public class AbstractCollection implements Collection<Integer> {
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        for (Integer x : c.toArray()) {
            this.add(x);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object x : c) {
            if (!this.contains(x)) {
                return false;
            };
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object x : c) {
            this.remove(x);
        }
        return true;
    }
}
