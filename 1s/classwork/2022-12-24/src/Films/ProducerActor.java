package Films;

public class ProducerActor extends Producer {
    private final ActorJob actorJob;
    public ProducerActor(String name, int age) {
        super(name, age);
        this.actorJob = ActorJob.getInstance();
    }
    @Override
    public String myProfession() {
        return "My name is " + this.getName() + " and I'm an actor-producer. I'm working for " + this.getWorkingAge() + " years";
    }
    public void repeatPhrases() {
        this.actorJob.repeatPhrases();
    }
    public void readScript() {
        this.actorJob.readScript();
    }
}
