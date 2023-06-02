import java.util.List;

public class User {
    private final String naym, city, mainInterest, bio, favColor, pet, futureProf;
    private final int year, id;

    public User(String[] args) {
        this.id = Integer.parseInt(args[0]);
        this.naym = args[1];
        this.city = args[2];
        this.year = Integer.parseInt(args[3]);
        this.mainInterest = args[4];
        this.bio = args[5];
        this.favColor = args[6];
        this.pet = args[7];
        this.futureProf = args[8];
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public String getBio() {
        return bio;
    }

    public String getFavColor() {
        return favColor;
    }

    public String getFutureProf() {
        return futureProf;
    }

    public String getMainInterest() {
        return mainInterest;
    }

    public String getNaym() {
        return naym;
    }

    public String getPet() {
        return pet;
    }
    public String toString() {
        return "<" + this.id + "; " + this.naym + "; " + this.year + "; " + this.city + "; " + this.mainInterest + "; " + this.bio + "; " + this.favColor + "; " + this.pet + "; " + this.futureProf + ">";
    }
}
