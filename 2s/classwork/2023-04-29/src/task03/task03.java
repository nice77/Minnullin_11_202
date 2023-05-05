package task03;

public class task03 {
    public static void main(String[] args) {
        SObj o1 = new SObj(), o2 = new SObj();
        MyThread thr1 = new MyThread(o1, o2);
        MyThread thr2 = new MyThread(o2, o1);
        thr1.start();
        thr2.start();
    }
}
