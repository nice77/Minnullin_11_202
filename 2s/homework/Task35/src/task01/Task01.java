package task01;


public class Task01 {
    public static void main(String[] args) {
        thCounter(arrCreator());
    }

    public static int[] arrCreator() {
        int[] arr = new int[(int) (Math.random() * 100)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public static void thCounter(int[] arr) {
        int size = 4; // размер пула
        Pool pool = new Pool(size);
//        pool.start();

        System.out.println(arr.length);
        int subArraySize = 7;

        if (arr.length < subArraySize) {
            pool.addTask(arr, 0, arr.length);
        }
        for (int i = 0; i < arr.length; i += arr.length / subArraySize) {
            System.out.println("interval: " + i + ", " + (i + arr.length / subArraySize));
//            while (!pool.addTask(arr, i, i + arr.length / subArraySize)) {
//                System.out.println("Waiting...");
//            }
            pool.addTask(arr, i, i + arr.length / subArraySize);
        }
        System.out.println(pool.getS());
        checkIfCorrect(arr);
    }

    public static void checkIfCorrect(int[] arr) {
        int s = 0;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        System.out.println("Correct: " + s);
    }
}
