package models;

public class Subscriptions extends AbstractModel {
    private int who_id;
    private int on_whom_id;

    public Subscriptions(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.who_id = Integer.parseInt(data[1]);
        this.on_whom_id = Integer.parseInt(data[2]);
    }

    public int getWho_id() {
        return who_id;
    }

    public int getOn_whom_id() {
        return on_whom_id;
    }

    @Override
    public int compareTo(AbstractModel o) {
        return Integer.compare(this.on_whom_id, ((Subscriptions) o).on_whom_id);
    }

    public String toString() {
        return "Subscriptions <" + who_id + "; " + on_whom_id + ">";
    }
}
