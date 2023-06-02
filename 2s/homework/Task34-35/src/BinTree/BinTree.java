package BinTree;


import models.AbstractModel;


public class BinTree<T extends AbstractModel> {
    private Node<T> root;

    public BinTree(T value) {
        this.root = new Node<T>(value);
    }

    public BinTree() {
        super();
    }

    public void add(T toAdd) {
        this.root = addToConcrete(this.root, toAdd);
    }

    private Node<T> addToConcrete(Node<T> node, T toAdd) {
        if (node == null) {
            return new Node<T>(toAdd);
        }
        if (toAdd.compareTo(node.getValue()) < 0) {
            node.setLeft(addToConcrete(node.getLeft(), toAdd));
        }
        else {
            node.setRight(addToConcrete(node.getRight(), toAdd));
        }
        return node;
    }

    public boolean contains(T toFind) {
        return containsRecursive(this.root, toFind);
    }

    private boolean containsRecursive(Node<T> node, T toFind) {
        if (node == null) {
            return false;
        }
        if (node.getValue().compareTo(toFind) == 0) {
            return true;
        }
        if (toFind.compareTo(node.getValue()) < 0) {
            return containsRecursive(node.getLeft(), toFind);
        }
        return containsRecursive(node.getRight(), toFind);
    }

    public void print() {
        printTree(this.root, 0);
    }

    private void printTree(Node<T> node, int d) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < d; i++) {
            System.out.print("-");
        }
        System.out.println(node);
        printTree(node.getLeft(), d + 1);
        printTree(node.getRight(), d + 1);
    }

    public Node<T> search(T template) {
        if (template.compareTo(this.root.getValue()) < 0) {
            return recursiveSearch(this.root.getLeft(), template);
        }
        else if (template.compareTo(this.root.getValue()) > 0) {
            return recursiveSearch(this.root.getRight(), template);
        }
        return this.root;
    }

    private Node<T> recursiveSearch(Node<T> node, T template) {
        if (node != null && template.compareTo(node.getValue()) < 0) {
            return recursiveSearch(node.getLeft(), template);
        }
        else if (node != null && template.compareTo(node.getValue()) > 0) {
            return recursiveSearch(node.getRight(), template);
        }
        return node;
    }

    public void remove(T elem) {
        Node<T> found = search(elem);
        found.setValue(recursiveRemove(this.root, elem).getValue());
    }

    private Node<T> recursiveRemove(Node<T> node, T elem) {
        Node<T> found = search(elem);
        if (found.getNumOfDes() == 0) {
            found = null;
        }
        else if (found.getNumOfDes() == 2) {
            found = findMinElement(found.getRight());
        }
        else if (found.getLeft() != null) {
            found = found.getLeft();
        }
        else if (found.getRight() != null) {
            found = found.getRight();
        }
        return found;
    }

    private Node<T> findMinElement(Node<T> node) {
        if (node.getLeft() != null) {
            if (node.getLeft().getLeft() == null) {
                Node<T> toReturn = node.getLeft();
                node.setLeft(node.getLeft().getRight());
                return toReturn;
            }
        }
        return findMinElement(node.getLeft());
    }
}
