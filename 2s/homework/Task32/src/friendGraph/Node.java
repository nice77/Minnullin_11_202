package friendGraph;

import models.User;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private User user;
    private List<User> friends;

    public Node(User user, List<User> friends) {
        this.user = user;
        this.friends = friends;
    }
    public Node() {
        super();
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }
}
