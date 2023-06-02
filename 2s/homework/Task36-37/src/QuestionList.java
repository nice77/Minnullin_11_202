import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuestionList {
    private static List<Question> questions;
    private static QuestionList instance;
    private static final List<Integer> correct = Arrays.asList(0, 0, 1, 3, 3, 0, 2, 0, 1, 3, 0, 1, 2, 1, 2, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 3, 3, 3, 0, 1, 0, 1, 2, 1, 2, 3, 2, 0, 0, 1, 2, 1, 3, 1, 1, 2, 1, 0);


    private QuestionList(UserList ul) {
        questions = Service.questionCreator(ul);
    }

    public static QuestionList getInstance(UserList ul) {
        if (instance == null) {
            instance = new QuestionList(ul);
        }
        return instance;
    }

    public Question getQuestion(int qID) {
        return questions.stream().filter(q -> q.getId() == qID).findFirst().get();
    }

    public static List<Question> getQuestions() {
        return questions;
    }

    public Question getEasiest() {
        return getQuestion(questions.stream()
                .map(q -> Arrays.asList(q.getId(), getCorrect(q.getId())))
                .max((q1, q2) -> Integer.compare(q1.get(1), q2.get(1)))
                .get()
                .get(0));
    }

    public Question getHardest() {
        return getQuestion(questions.stream()
                .map(q -> Arrays.asList(q.getId(), getCorrect(q.getId())))
                .max((q1, q2) -> Integer.compare(-q1.get(1), -q2.get(1)))
                .get()
                .get(0));
    }


    public Integer getCorrect(int qID) {
        return (int) IntStream.range(0, questions.get(qID).getAnswers().size())
                .mapToObj(i -> questions.get(qID).getAnswers().get(i).equals(correct.get(qID)))
                .filter(i -> i)
                .count();
    }

    public int easinessIndex(int qID) {
        return (int) ((getCorrect(qID) / ((double) questions.get(0).getAnswers().size())) * 100);
    }

    public int DiscriminationIndex(int qID) {
        List<Integer> out = new ArrayList<>();
        int corrCount, avg = 0;
        for (int i = 0; i < questions.get(0).getAnswers().size(); i++) {
            corrCount = 0;
            for (Question q : questions) {
                if (q.getAnswers().get(i).equals(correct.get(i))) {
                    corrCount++;
                }
            }
            out.add(corrCount);
            avg += corrCount;
        }
        avg = avg / questions.get(0).getAnswers().size();

        List<Integer> goods = new ArrayList<>();
        for (int i = 0; i < out.size(); i++) {
            if (out.get(i) >= avg) {
                goods.add(i);
            }
        }

        int goodsCnt = 0, badsCnt = 0;
        for (int i = 0; i < questions.get(qID).getAnswers().size(); i++) {
            if (questions.get(qID).getAnswers().get(i).equals(correct.get(i))) {
                if (goods.contains(i)) {
                    goodsCnt++;
                }
                else {
                    badsCnt++;
                }
            }
        }
        return (int) (((((double) goodsCnt) / ((double) goods.size())) - (((double) badsCnt) / ((double) (out.size() - goods.size())))) * 100);
    }
}
