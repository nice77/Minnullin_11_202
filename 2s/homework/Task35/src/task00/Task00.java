package task00;

public class Task00 {
    public static void main(String[] args) {
        int m = 20;
        MyThread[] arr = new MyThread[m];
        for (int i = 0; i < m; i++) {
            arr[i] = new MyThread(m);
            arr[i].start();
        }
    }
}
