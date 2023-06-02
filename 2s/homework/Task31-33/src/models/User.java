package models;

public class User extends AbstractModel {
    private String name, surname, city;
    private int year;

    public User(String[] data) {
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.surname = data[2];
        this.city = data[3];
        this.year = Integer.parseInt(data[4]);
    }

    public String getCity() {
        return city;
    }

    public String toString() {
        return "User <" + this.id + " " + this.name + " " + this.surname + ">";
    }
}
