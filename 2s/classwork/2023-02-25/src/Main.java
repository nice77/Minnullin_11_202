public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> array = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i);
        }
        LinkedList<Integer> arr = new LinkedList<>(array.head, 3, 7);
        for (int i = 2; i < 5; i++) {
            System.out.println("kek");
        }
    }
}
