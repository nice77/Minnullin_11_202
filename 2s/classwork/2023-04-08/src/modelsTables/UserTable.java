package modelsTables;

import models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserTable extends AbstractTable {
    private static UserTable instance;

    private UserTable() {
        super();
    }

    public static UserTable getInstance() {
        if (instance == null) {
            instance = new UserTable();
        }
        return instance;
    }

    public String toString() {
        return this.table.toString();
    }

    public User getRowById(int id) {
        return (User) super.getRowById(id);
    }
}
