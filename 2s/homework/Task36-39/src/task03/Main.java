package task03;

public class Main {
    public static void main(String[] args) {
        TestRunner tr = new TestRunner(TestCases.class);
        try {
            tr.run();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
