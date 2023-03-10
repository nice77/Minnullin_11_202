import java.util.Comparator;

public class ComparatorStrHam implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        if (o1.length() != o2.length()) {
            throw new RuntimeException();
        }
        int diff = 0;
        for (int i = 0; i < o1.length(); i++) {
            if (o1.charAt(i) != o2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }
}
