package control;

public class Birth {
    private final char gender;
    private final String race;
    private final int weight;
    private final boolean smoked;
    private final int weeks;
    private final int educationAge;
    public Birth(char gender, String race, int weight, boolean smoked, int weeks, int educationAge) {
        this.gender = gender;
        this.race = race;
        this.weight = weight;
        this.smoked = smoked;
        this.weeks = weeks;
        this.educationAge = educationAge;
    }

    public char getGender() {
        return gender;
    }

    public int getEducationAge() {
        return educationAge;
    }

    public boolean isSmoked() {
        return smoked;
    }

    public int getWeight() {
        return weight;
    }

    public int getWeeks() {
        return weeks;
    }

    public String getRace() {
        return race;
    }

    public String toString() {
        return "<" + "Gender: " + this.gender + "; Race: " + this.race + "; Weight: " + this.weight + ">";
    }
}
