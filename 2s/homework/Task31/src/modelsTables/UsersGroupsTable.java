package modelsTables;

import models.Group;
import models.User;
import models.UsersGroups;

import java.util.ArrayList;

public class UsersGroupsTable extends AbstractTable {
    private static UsersGroupsTable instance;

    private UsersGroupsTable() {
        this.table = new ArrayList<>();
    }

    public static UsersGroupsTable getInstance() {
        if (instance == null) {
            instance = new UsersGroupsTable();
        }
        return instance;
    }

    public UsersGroups getRowById(int id) {
        return (UsersGroups) super.getRowById(id);
    }

    public boolean groupContains(User user, Group group) {
        return this.table.stream()
                .map(r -> (UsersGroups) r)
                .anyMatch(r -> r.getUserId() == user.getId() && r.getGroupId() == group.getId());
    }

    public String toString() {
        return this.table.toString();
    }
}
