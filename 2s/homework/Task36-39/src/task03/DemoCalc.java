package task03;

public class DemoCalc {
    private final int uno, dos;
    public DemoCalc(int uno, int dos) {
        this.uno = uno;
        this.dos = dos;
    }

    public int getSum() {
        return this.uno + this.dos;
    }

    public int getDiff() {
        return this.uno - this.dos;
    }

    public int getUno() {
        return this.uno;
    }

    public int getDos() {
        return this.dos;
    }
}
