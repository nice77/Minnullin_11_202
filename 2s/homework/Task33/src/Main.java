import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        UserList ul = UserList.getInstance();
        for (User user : ul.getUsers()) {
            System.out.println(user);
        }

        QuestionList ql = QuestionList.getInstance(ul);
        for (Question question : QuestionList.getQuestions()) {
            System.out.println(question);
            System.out.println(ql.DiscriminationIndex(question.getId()));
        }
    }
}
