package Films;

public class ActorJob {
    private static ActorJob instance;
    private ActorJob() {}
    public static ActorJob getInstance() {
        if (instance == null) {
            instance = new ActorJob();
        }
        return instance;
    }
    public void repeatPhrases() {
        System.out.println("Repeating phrases...");
    }
    public void readScript() {
        System.out.println("Reading script...");
    }
}
