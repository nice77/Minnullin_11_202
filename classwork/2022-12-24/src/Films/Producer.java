package Films;

public class Producer extends Human {
    public Producer(String name, int age) {
        super(name, age);
    }
    @Override
    public String myProfession() {
        return "My name is " + this.getName() + " and I'm a producer. I'm working for " + this.getWorkingAge() + " years";
    }
    public void writeScript() {
        System.out.println("Making new blockbuster's script...");
    }
    public void showTheWork() {
        System.out.println("Showing the work to headquarters...");
    }
}
