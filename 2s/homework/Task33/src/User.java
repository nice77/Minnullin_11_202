import java.util.ArrayList;

public class User {
    private int id;
    private ArrayList<Integer> answers;
    private int time;

    public User(int id, ArrayList<Integer> answers, int time) {
        this.id = id;
        this.answers = answers;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getTime() {
        return time;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public Integer getCurrentAnswer(int index) {
        return answers.get(index);
    }

    public String toString() {
        return "<User ID: " + this.id + "\nAnswers: " + this.answers + "\nTime: " + this.time + ">";
    }
}
