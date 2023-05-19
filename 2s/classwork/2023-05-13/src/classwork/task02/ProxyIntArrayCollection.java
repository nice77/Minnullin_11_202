package classwork.task02;

import java.util.LinkedList;
import java.util.Queue;

public class ProxyIntArrayCollection {
    private IntArrayCollection arr;
    private Queue<Integer> addQueue;
    public ProxyIntArrayCollection() {
        this.arr = new IntArrayCollection();
        this.addQueue = new LinkedList<>();
    }

    public void add(int elem) {
        this.addQueue.add(elem);
    }

    public int size() {
        while (this.addQueue.size() != 0) {
            this.arr.add(this.addQueue.poll());
        }
        return this.arr.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean remove(Integer val) {
        while (this.addQueue.size() != 0) {
            this.arr.add(this.addQueue.poll());
        }
        return this.arr.remove(val);
    }
}
