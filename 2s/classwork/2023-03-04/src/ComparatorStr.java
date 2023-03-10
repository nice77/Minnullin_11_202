import java.util.Comparator;

public class ComparatorStr implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        for (int i = 0; i < Math.min(o1.length(), o2.length()); i++) {
            if (o1.charAt(i) > o2.charAt(i)) {
                return 1;
            }
            else if (o1.charAt(i) < o2.charAt(i)) {
                return -1;
            }
        }
        return 0;
    }
}
