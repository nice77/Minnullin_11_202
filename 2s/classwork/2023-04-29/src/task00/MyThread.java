package task00;

public class MyThread extends Thread {
    private int m;

    public MyThread(int m) {
        this.m = m;
    }
    public void run() {
        for (int i = 0; i < m; i++) {
            System.out.println(getName() + ": " + i);
        }
    }
}
