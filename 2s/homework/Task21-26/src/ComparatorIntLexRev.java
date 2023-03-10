import java.util.Comparator;



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
