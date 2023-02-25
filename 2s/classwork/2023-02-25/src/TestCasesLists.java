import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TestCasesLists {

    public List<Integer> creator() {
        List<Integer> arr = new ArrayList();
        for (int i = 0; i < 5; i++) {
            arr.add(i);
        }
        return arr;
    }

    @Test
    public void addedSize() {
        List<Integer> arr = creator();
        Assert.assertEquals(5, arr.size());
    }

    @Test
    public void setGetElem() {
        List<Integer> arr = creator();
        arr.set(0, 5);
        Assert.assertEquals(5, arr.get(0).intValue());
    }

    @Test
    public void removeByIndex() {
        List<Integer> arr = creator();
        arr.remove(5);
        arr.remove(4);
        Assert.assertEquals(4, arr.size());
    }
    @Test
    public void indexOf() {
        List<Integer> arr = creator();
        Assert.assertEquals(3, arr.indexOf(3));
    }
    @Test
    public void lastIndexOf() {
        List<Integer> arr = creator();
        Assert.assertEquals(3, arr.lastIndexOf(3));
    }

    @Test
    public void lenSubList() {
        List<Integer> arr = creator();
        Assert.assertEquals(3, arr.subList(1, 4).size());
    }
}
