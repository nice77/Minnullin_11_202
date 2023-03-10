import java.util.Comparator;

// log10(10) = 1
// log10(100) = 2
// log10(1000) = 3
// (1 / 1) % 10 (k = 1)
// (1002 / 10 ** 3) % 10 (k = 4)

// (1 / 1) % 10 (k = 1)
// (2001 / 1) % 10 (k = 4)

public class ComparatorIntLexRev implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        int sizeUno = (int) Math.ceil(Math.log10(o1.doubleValue()));
        int sizeDos = (int) Math.ceil(Math.log10(o2.doubleValue()));
        int uno, dos;
        for (int i = 0; i < Math.min(sizeUno, sizeDos); i++) {
            uno = o1.intValue() % 10;
            dos = o2.intValue() % 10;
            if (uno != dos) {
                System.out.println(uno + " " + dos);
                return uno - dos;
            }
            o1 /= 10;
            o2 /= 10;
        }
        return sizeUno - sizeDos;
    }
}
