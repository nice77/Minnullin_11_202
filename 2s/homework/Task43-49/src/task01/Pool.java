package task01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Pool {//extends Thread{
    int size, s, capacity;
    List<MyThread> thList;
    Queue<MyThread> queue;

    public Pool(int size) {
        this.size = size;
        this.thList = new ArrayList<>(this.size);
        this.queue = new LinkedList<>();
        this.s = 0;
    }

    public boolean addTask(int[] arr, int start, int end) {
        this.queue.add(new MyThread(arr, start, end));
        System.out.println("Queue: " + this.queue);
        for (int i = 0; i < this.size; i++) {
            if (this.thList.size() < this.size) {
                this.thList.add(new MyThread(arr, start, end));
                this.thList.get(this.thList.size() - 1).start();
                return true;
            }
            if (!this.thList.get(i).isAlive()) {
                this.s += this.thList.get(i).getS();
                this.thList.set(i, new MyThread(arr, start, end));
                this.thList.get(i).start();
                return true;
            }
        }
        return false;
    }

    public int getS() {
        for (int i = 0; i < this.size; i++) {
            try {
                this.thList.get(i).join();
                s += this.thList.get(i).getS();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return this.s;
    }

//    public void run() {
//        System.out.println("running");
//        for (int i = 0; i < this.thList.size(); i++) {
//            if (!this.thList.get(i).isAlive()) {
//                this.s += this.thList.get(i).getS();
//                if (this.queue.peek() != null) {
//                    this.thList.set(i, this.queue.poll());
//                    System.out.println("Queue after deleting: " + this.queue);
//                    this.thList.get(i).start();
//                }
//            }
//        }
//    }
}
