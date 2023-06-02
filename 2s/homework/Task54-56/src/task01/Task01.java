package classwork.task01;

public class Task01 {
    public static void main(String[] args) {
        TestObj orig = new TestObj(2);
        TestObj copy = (new CopyMachine<TestObj>(orig)).copy();
        System.out.println(copy);
    }
}
