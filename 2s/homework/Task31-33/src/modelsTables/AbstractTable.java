package modelsTables;

import models.AbstractModel;

import java.util.List;

public abstract class AbstractTable {
    protected List<AbstractModel> table;
    public AbstractModel getRowById(int id) {
        return this.table.stream().filter(r -> r.getId() == id).findFirst().get();
    }
    public List<AbstractModel> getTable() {
        return this.table;
    }
    public void addElem(AbstractModel elem) {
        this.table.add(elem);
    }
}
