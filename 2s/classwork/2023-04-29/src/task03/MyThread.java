package task03;

public class MyThread extends Thread {
    private SObj o1, o2;
    public MyThread(SObj o1, SObj o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public void run() {
        synchronized (o1) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (o2) {
                System.out.println(o1.toString());
                System.out.println(o2.toString());
            }
        }
    }
}
