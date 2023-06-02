package modelsTables;

import models.Subscriptions;

import java.util.ArrayList;

public class SubscriptionsTable extends AbstractTable {
    private static SubscriptionsTable instance;

    private SubscriptionsTable() {
        super();
    }

    public static SubscriptionsTable getInstance() {
        if (instance == null) {
            instance = new SubscriptionsTable();
        }
        return instance;
    }

    public Subscriptions getRowById(int id) {
        return (Subscriptions) super.getRowById(id);
    }

    public String toString() {
        return this.table.toString();
    }
}
