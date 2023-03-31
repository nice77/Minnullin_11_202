public class Subscription {
    private final User userOne, userTwo;
    private final int id;

    public Subscription(int id, User userOne, User userTwo) {
        this.id = id;
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public User getSubscriber() {
        return this.userOne;
    }

    public User getSubscription() {
        return this.userTwo;
    }

    public int getId() {
        return this.id;
    }
}
