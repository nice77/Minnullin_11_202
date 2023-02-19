import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        IntArrayCollection iac = new IntArrayCollection();
        Collection<Integer> removable = new IntArrayCollection();
        for (int i = 0; i < 10; i++) {
            removable.add(i);
        }
//        iac.removeAll(removable);
        System.out.println(iac.size());
    }
}
