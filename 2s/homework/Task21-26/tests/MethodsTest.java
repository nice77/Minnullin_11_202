import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class MethodsTest {
    @Test
    public void compareIntegersUno() {
        Assert.assertEquals(1337 - 1048, new ComparatorInt().compare(1337, 1048));
    }
    @Test
    public void compareIntegersDos() {
        Assert.assertEquals(17 - 1048, new ComparatorInt().compare(17, 1048));
    }

    @Test
    public void compareStringsUno() {
        Assert.assertEquals(0, new ComparatorStr().compare("Nice", "Nicest"));
    }
    @Test
    public void compareStringsDos() {
        Assert.assertEquals(-1, new ComparatorStr().compare("Дезоксирибонуклеиновая", "Кислота"));
    }

    @Test
    public void compareStringsHamUno() {
        Assert.assertEquals(2, new ComparatorStrHam().compare("Nice", "Nose"));
    }
    @Test
    public void compareStringsHamDos() {
        Assert.assertEquals(2, new ComparatorStrHam().compare("Rope", "Nose"));
    }

    @Test
    public void compareIntLexUno() {
        Assert.assertEquals(1, new ComparatorIntLex().compare(1074, 107));
    }
    @Test
    public void compareIntLexDos() {
        Assert.assertEquals(-6, new ComparatorIntLex().compare(1337, 783));
    }
    @Test
    public void compareStrLen() {
        Assert.assertEquals(1, new ComparatorStrLen().compare("Nice", "Uno"));
    }

    @Test
    public void compareIntLexRevUno() {
        Assert.assertEquals(-1, new ComparatorIntLexRev().compare(2003, 103));
    }
    @Test
    public void compareIntLexRevDos() {
        Assert.assertEquals(3, new ComparatorIntLexRev().compare(107, 1074));
    }
    @Test
    public void sortingArrList() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(2331234);
        arr.add(134);
        arr.add(12889);
        Collections.sort(arr, new ComparatorIntLex());
        Assert.assertEquals(1, new ComparatorIntLex().compare(134, 12889));
        Assert.assertEquals("[12889, 134, 2331234]", arr.toString());
    }
}
