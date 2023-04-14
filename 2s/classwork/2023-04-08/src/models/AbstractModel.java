package models;

public abstract class AbstractModel implements Comparable<AbstractModel> {
    protected int id;

    public int getId() {
        return this.id;
    }

}
