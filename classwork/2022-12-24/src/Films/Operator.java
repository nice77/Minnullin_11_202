package Films;

public class Operator extends Human{
    public Operator(String name, int age) {
        super(name, age);
    }

    @Override
    public String myProfession() {
        return "My name is " + this.getName() + " and I'm an operator. I'm working for " + this.getWorkingAge() + " years";
    }

    public void cleanupCamera() {
        System.out.println("Cleaning camera...");
    }
    public void shootFootage() {
        System.out.println("Shooting some example footage...");
    }
}
