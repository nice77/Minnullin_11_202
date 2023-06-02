package classwork.task02;

import java.util.Collection;

abstract public class AbstractCollection implements Collection<Integer> {
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        for (Object o : c.toArray()) {
            this.add((Integer) o);
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
