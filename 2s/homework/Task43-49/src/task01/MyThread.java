package task01;

public class MyThread extends Thread{
    int[] arr;
    int start, end, s;
    public MyThread(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.s = 0;
    }

    public void run() {
        for (int i = this.start; i < this.arr.length && i < this.end; i++) {
            this.s += arr[i];
        }
    }

    public int getS() {
        return this.s;
    }
}
