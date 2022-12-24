package Films;

public class Actor extends Human{
    private final ActorJob actorJob;
    public Actor(String name, int age) {
        super(name, age);
        this.actorJob = ActorJob.getInstance();
    }
    @Override
    public String myProfession() {
        return "My name is " + this.getName() + " and I'm an actor. I'm working for " + this.getWorkingAge() + " years";
    }

    public void repeatPhrases() {
        this.actorJob.repeatPhrases();
    }
    public void readScript() {
        this.actorJob.readScript();
    }
}
