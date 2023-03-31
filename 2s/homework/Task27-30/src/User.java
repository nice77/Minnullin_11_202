public class User {
    private final String surname;
    private final int year, id;
    public User(int id, String surname, int year) {
        this.id = id;
        this.surname = surname;
        this.year = year;
    }

    public int getYear() {
        return this.year;
    }

    public String getSurname() {
        return this.surname;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return this.id + " " + this.surname + " " + this.year;
    }
}
