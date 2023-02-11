public class ComparedUser {
    private final String name;
    private final String data;
    private final int percent;
    public ComparedUser(String name, int percent, String data) {
        this.name = name;
        this.percent = percent;
        this.data = data;
    }
    public String toString() {
        return this.name + ": " + this.percent;
    }
    public int getPercent() {
        return this.percent;
    }
    public char getDataAt(int index) {
        return this.data.charAt(index);
    }
}
