package classwork.task01;

public class TestObj {
    private int uno;
    public TestObj(int uno) {
        this.uno = uno;
    }
    public TestObj() {}
    public int getUno() {
        return uno;
    }
    public void setUno(int uno) {
        this.uno = uno;
    }
    public String toString() {
        return "Parameter uno: " + this.uno;
    }
}
