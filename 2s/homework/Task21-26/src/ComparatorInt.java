import java.util.Comparator;

public class ComparatorInt implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
