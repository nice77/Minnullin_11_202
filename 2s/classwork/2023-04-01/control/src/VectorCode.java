public class VectorCode {
    private LinkedList list;
    private int origLength;
    public VectorCode(int[] arr) {
        this.list = new LinkedList();
        this.origLength = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != 0) {
                this.list.add(new int[]{i, arr[i]});
            }
        }
    }

    public int[] decode() {
        int[] out = new int[this.origLength];
        int i = 0;
        for (Elem p = this.list.getHead(); p != null; p = p.getNext()) {
            if (p.getValue()[0] != i) {
                while (i != p.getValue()[0]) {
                    out[i] = 0;
                    i++;
                }
            }
            out[i] = p.getValue()[1];
            i++;
        }
        return out;
    }

    public void insert(int k, int pos) {
        Elem p = this.list.getHead();
        while (p.getNext() != null && p.getNext().getValue()[0] < pos) {
            p = p.getNext();
        }
        if (p.getNext() != null) {
            if (p.getValue()[0] == pos) {
                p.setValue(new int[] {pos, k});
            }
            else {
                Elem addable = new Elem(new int[] {pos, k});
                Elem toChange = p.getNext();
                while (toChange != null) {
                    toChange.setValue(new int[] {toChange.getValue()[0] + 1, toChange.getValue()[1]});
                    toChange = toChange.getNext();
                }
                addable.setNext(p.getNext());
                p.setNext(addable);
            }
        }
        else {
            if (p.getValue()[0] == pos) {
                p.setValue(new int[] {pos, k});
            }
            else {
                p.setNext(new Elem(new int[] {pos, k}));
            }
        }
        this.origLength++;
    }

    public void delete(int pos) {
        Elem p = this.list.getHead();
        while (p.getNext() != null) {
            if (p.getNext().getValue()[0] == pos) {
                p.setNext(p.getNext().getNext());
                return;
            }
            p = p.getNext();
        }
        if (p.getValue()[0] == pos) {
            p = null;
        }
    }

    public int scalarProduct(VectorCode other) {
        int out = 0;
        for (Elem p1 = this.list.getHead(); p1 != null; p1 = p1.getNext()) {
            for (Elem p2 = other.list.getHead(); p2 != null; p2 = p2.getNext()) {
                if (p2.getValue()[0] > p1.getValue()[0]) {
                    break;
                }
                if (p1.getValue()[0] == p2.getValue()[0]) {
                    out += p1.getValue()[1] * p2.getValue()[1];
                }
            }
        }
        return out;
    }

    public VectorCode sum(VectorCode other) {
        int[] vector = new int[Math.max(this.origLength, other.origLength)];
        for (int i = 0; i <  vector.length; i++) {
            vector[i] = 0;
        }
        this.sumHelper(vector);
        other.sumHelper(vector);
        return new VectorCode(vector);
    }

    private void sumHelper(int[] vector) {
        int i = 0;
        for (Elem p = this.list.getHead(); p != null; p = p.getNext()) {
            if (p.getValue()[0] != i) {
                while (i < p.getValue()[0]) {
                    i++;
                }
            }
            vector[i] += p.getValue()[1];
        }
    }

    public VectorCode vectorSum() {
        int[] vector = new int[this.origLength];
        for (int i = 0; i < this.origLength; i++) {
            vector[i] = vSumHelper(i);
        }
        return new VectorCode(vector);
    }

    private int vSumHelper(int i) {
        Elem p = this.list.getHead();
        int out = 0;
        for (int j = 0; j <= i; j++) {
            if (p != null && p.getValue()[0] == j) {
                out += p.getValue()[1];
                p = p.getNext();
            }
        }
        return out;
    }

    public LinkedList getList() {
        return this.list;
    }

    public int getOrigLength() {
        return this.origLength;
    }
}
