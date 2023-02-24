import org.junit.Test;
import org.junit.Assert;
import java.util.Collection;

public class TestCasesCollections {

    @Test
    public void addedArrayAllSize() {
        Collection<Integer> arr = new IntLinkedCollection();
        Collection<Integer> toAdd = new IntLinkedCollection();
        for (int i = 0; i < 10; i++) {
            toAdd.add(i);
        }
        arr.addAll(toAdd);
        Assert.assertEquals(toAdd.size(), arr.size());
    }

    @Test
    public void removedArrayAllSize() {
        Collection<Integer> arr = new IntLinkedCollection();
        Collection<Integer> removable = new IntLinkedCollection();
        for (int i = 0; i < 10; i++) {
            removable.add(i);
        }
        arr.addAll(removable);
        arr.removeAll(removable);
        Assert.assertEquals(0, arr.size());
    }

    @Test
    public void isEmpty() {
        Collection<Integer> arr = new IntArrayCollection();
        Assert.assertTrue(arr.isEmpty());
    }

    @Test
    public void contains() {
        Collection<Integer> arr = new IntArrayCollection();
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        Assert.assertTrue(arr.contains(0));
    }

    @Test
    public void remove() {
        Collection<Integer> arr = new IntArrayCollection();
        for (int i = 0; i <  5; i++) {
            arr.add(i);
        }
        arr.remove(0);
        Assert.assertEquals(4, arr.size());
    }

    @Test
    public void retainAll() {
        Collection<Integer> arr = new IntArrayCollection();
        Collection<Integer> retainable = new IntArrayCollection();
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        retainable.add(4);
        retainable.add(5);
        arr.retainAll(retainable);
        Assert.assertEquals(1, arr.size());
    }

    @Test
    public void clear() {
        Collection<Integer> arr = new IntArrayCollection();
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        arr.clear();
        Assert.assertEquals(0, arr.size());
    }
}
