package modelsTables;

import models.Group;

import java.util.ArrayList;

public class GroupTable extends AbstractTable {
    private static GroupTable instance;

    private GroupTable() {
        this.table = new ArrayList<>();
    }

    public static GroupTable getInstance() {
        if (instance == null) {
            instance = new GroupTable();
        }
        return instance;
    }

    public Group getRowById(int id) {
        return (Group) super.getRowById(id);
    }

    public String toString() {
        return this.table.toString();
    }
}
