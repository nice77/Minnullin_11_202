import org.junit.Test;
import org.junit.Assert;

import java.util.Collection;

public class Tester {

    @Test
    public void removedAllSize() {
        Collection<Integer> arr = new IntArrayCollection();
        Collection<Integer> removable = new IntArrayCollection();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
            removable.add(i);
        }
        arr.removeAll(removable);

        Assert.assertEquals(0, arr.size());
    }
}
