import java.util.List;

public class Question {
    private int id;
    private List<Integer> answers;

    public Question(int id, List<Integer> answers) {
        this.id = id;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public String toString() {
        return "<" + this.id + "; Answers: " + this.answers + ">";
    }
}
