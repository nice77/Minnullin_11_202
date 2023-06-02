package task03;

import task03.markers.After;
import task03.markers.Before;
import task03.markers.Test;

public class TestCases {
    private int uno, dos;
    private DemoCalc dc;

    @Before
    public void starter() {
        uno = 4;
        dos = 5;
        dc = new DemoCalc(uno, dos);
    }

    // Will success
    @Test
    public void testAdd() {
        if (uno + dos != dc.getSum()) {
            throw new AssertionError();
        }
    }
    // Will fail
    @Test
    public void testDiff() {
        if (1330 != dc.getDiff()) {
            throw new AssertionError();
        }
    }

    @After
    public void clear() {
        System.out.println("Cleaned");
    }
}
