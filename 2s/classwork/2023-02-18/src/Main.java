import java.util.Collection;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Collection<Integer> arr = new IntLinkedCollection();
        Collection<Integer> toAdd = new IntLinkedCollection();
        for (int i = 0; i < 3; i++) {
            toAdd.add(i);
        }
        arr.addAll(toAdd);
        System.out.println(Arrays.toString(arr.toArray()) + "; " + arr.size());
        arr.removeAll(toAdd);
        System.out.println(Arrays.toString(arr.toArray()));
    }
}
