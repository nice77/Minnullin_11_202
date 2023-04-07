package modelsTables;

import models.*;

public class TableFactory {
    public static AbstractTable tableCreator(String name) {
        switch (name) {
            case "user":
                return UserTable.getInstance();
            case "group":
                return GroupTable.getInstance();
            case "users_groups":
                return UsersGroupsTable.getInstance();
            case "subscriptions":
                return SubscriptionsTable.getInstance();
        }
        return null;
    }
}
