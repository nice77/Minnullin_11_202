package models;

public class Group extends AbstractModel {
    private final String name, city;

    public Group(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.city = data[2];
    }

    public String getCity() {
        return city;
    }

    public String toString() {
        return "Group <" + this.name + ">";
    }
}
