package BinTree;


import models.AbstractModel;

public class Node<T extends AbstractModel> {
    private T value;
    private Node<T> left;
    private Node<T> right;
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public T getValue() {
        return this.value;
    }

    public Node<T> getLeft() {
        return this.left;
    }

    public Node<T> getRight() {
        return this.right;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void setValue(T value) { this.value = value; }

    public String toString() {
        return this.value.toString();
    }

    public int getNumOfDes() {
        return this.left == null ? this.right == null ? 0 : 1 : 2;
    }
}
