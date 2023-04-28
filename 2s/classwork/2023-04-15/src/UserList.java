import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class UserList {
    private static List<User> users;
    private static UserList instance;

    private static final List<Integer> correct = Arrays.asList(0, 0, 1, 3, 3, 0, 2, 0, 1, 3, 0, 1, 2, 1, 2, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 3, 3, 3, 0, 1, 0, 1, 2, 1, 2, 3, 2, 0, 0, 1, 2, 1, 3, 1, 1, 2, 1, 0);

    private UserList() {
        users = Service.userCreator();
    }

    public static UserList getInstance() {
        if (instance == null) {
            instance = new UserList();
        }
        return instance;
    }

    public User getFastestUser() {
        return users.stream().max((u1, u2) -> Integer.compare(-u1.getTime(), -u2.getTime())).get();
    }

    public User getSlowestUser() {
        return users.stream().max((u1, u2) -> Integer.compare(u1.getTime(), u2.getTime())).get();
    }

    public User getUser(int index) {
        return users.stream().filter(u -> u.getId() == index).findFirst().get();
    }

    public Integer getUserScore(User user) {
        return (int) IntStream.range(0, user.getAnswers().size())
                .mapToObj(i -> user.getAnswers().get(i).equals(correct.get(i)))
                .filter(i -> i)
                .count();
    }

    public User getHighestScore() {
        return getUser(users.stream()
                .map(u -> Arrays.asList(u.getId(), getUserScore(u)))
                .max((u1, u2) -> Integer.compare(u1.get(1), u2.get(1)))
                .get()
                .get(0));
    }

    public User getLowestScore() {
        return getUser(users.stream()
                .map(u -> Arrays.asList(u.getId(), getUserScore(u)))
                .max((u1, u2) -> Integer.compare(-u1.get(1), -u2.get(1)))
                .get()
                .get(0));
    }

    public List<User> getUsers() {
        return users;
    }
}
