import java.util.Comparator;


public class ComparatorIntLex implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
        int sizeUno = (int) Math.floor(Math.log10(o1.doubleValue()));
        int sizeDos = (int) Math.floor(Math.log10(o2.doubleValue()));
        int uno, dos;
        for (int i = 0; i <= Math.min(sizeUno, sizeDos); i++) {
            uno = (o1 / (int) Math.pow(10, sizeUno - i));
            dos = (o2 / (int) Math.pow(10, sizeDos - i));
            o1 %= (int) Math.pow(10, sizeUno - i);
            o2 %= (int) Math.pow(10, sizeDos - i);
            if (uno != dos) {
                return uno - dos;
            }
        }
        return sizeUno - sizeDos;
    }
}
