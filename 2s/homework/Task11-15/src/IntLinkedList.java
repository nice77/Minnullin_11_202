import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class IntLinkedList extends IntLinkedCollection implements List<Integer> {
    public IntLinkedList() {
        super();
    }
    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        return false;
    }

    @Override
    public Integer get(int index) {
        Elem pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i == index) {
                return pointer.getValue();
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    @Override
    public Integer set(int index, Integer element) {
        Elem pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i == index) {
                Integer temp = pointer.getValue();
                pointer.setValue(element);
                return temp;
            }
            pointer = pointer.getNext();
        }
        return null;
    }

    @Override
    public void add(int index, Integer element) {
        Elem pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i + 1 == index) {
                Elem toAdd = new Elem(element, pointer.getNext());
                pointer.setNext(toAdd);
                return;
            }
            pointer = pointer.getNext();
            i++;
        }
    }

    @Override
    public Integer remove(int index) {
        Elem pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (i + 1 == index) {
                Integer temp = pointer.getNext().getValue();
                if (pointer.getNext().getNext() != null) {
                    pointer.setNext(pointer.getNext().getNext());
                }
                else {
                    pointer.setNext(null);
                }
                return temp;
            }
            pointer = pointer.getNext();
            i++;
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        Elem pointer = this.head;
        int i = 0;
        while (pointer.getNext() != null) {
            if (pointer.getValue().equals(o)) {
                return i;
            }
            pointer = pointer.getNext();
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Elem pointer = this.head;
        int i = 0, max = 0;
        while (pointer.getNext() != null) {
            if (pointer.getValue().equals(o)) {
                max = i;
            }
            pointer = pointer.getNext();
            i++;
        }
        return max;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        int i = 0;
        Elem pointer = this.head;
        Integer[] temp = new Integer[toIndex - fromIndex];
        while (pointer.getNext() != null) {
            if (i >= fromIndex && i < toIndex) {
                temp[i - fromIndex] = pointer.getValue();
            }
            i++;
            pointer = pointer.getNext();
        }
        List<Integer> out = new IntLinkedList();
        for (int j = toIndex - fromIndex; j > 0; j--) {
            out.add(temp[i]);
        }
        return out;
    }
}
