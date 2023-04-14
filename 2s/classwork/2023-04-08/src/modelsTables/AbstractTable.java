package modelsTables;

import BinTree.BinTree;
import models.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTable {
    protected List<AbstractModel> table;
    protected BinTree<AbstractModel> tree;
    public AbstractTable() {
        this.table = new ArrayList<>();
        this.tree = new BinTree<AbstractModel>();
    }
    public AbstractModel getRowById(int id) {
        return this.table.stream().filter(r -> r.getId() == id).findFirst().get();
    }
    public List<AbstractModel> getTable() {
        return this.table;
    }
    public BinTree<AbstractModel> getTree() {
        return this.tree;
    }
    public void addElem(AbstractModel elem) {
        this.table.add(elem);
    }
    public void addElemTree(AbstractModel elem) {
        this.tree.add(elem);
    }
}
