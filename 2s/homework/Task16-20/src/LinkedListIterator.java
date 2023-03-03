import java.util.ListIterator;

public class LinkedListIterator<T> extends LinkedCollectionIterator<T> implements ListIterator<T> {
    private int pointer;
    public LinkedListIterator(Elem<T> head, int size, int startPos) {
        super(head, size);
        this.pointer = 0;
        while (this.pointer != startPos) {
            this.point = this.point.getNext();
            this.pointer++;
        }
    }


    @Override
    public boolean hasPrevious() {
        return this.point != this.head;
    }

    @Override
    public T previous() {
        if (this.hasPrevious()) {
            Elem<T> temp = this.head;
            while (temp.getNext() != this.point) {
                temp = temp.getNext();
            }
            return temp.getValue();
        }
        return null;
    }

    @Override
    public int nextIndex() {
        return this.pointer + 1 < size ? this.pointer + 1 : this.pointer;
    }

    @Override
    public int previousIndex() {
        return this.pointer - 1 > 0 ? this.pointer - 1 : this.pointer;
    }

    @Override
    public void remove() {
        Elem<T> temp = this.head;
        if (temp == this.point) {
            return;
        }
        if (temp.getNext() == this.point) {
            this.head = this.point.getNext();
            this.size--;
            return;
        }
        while (temp.getNext().getNext() != this.point) {
            temp = temp.getNext();
        }
        temp.setNext(this.point);
        this.size--;
    }

    @Override
    public void set(T t) {
        Elem<T> temp = this.head;
        while (temp.getNext() != this.point) {
            temp = temp.getNext();
        }
        temp.setValue(t);
    }

    @Override
    public void add(T t) {
        Elem<T> temp = new Elem<>(t, this.point.getNext());
        this.point.setNext(temp);
        this.size++;
    }
}
