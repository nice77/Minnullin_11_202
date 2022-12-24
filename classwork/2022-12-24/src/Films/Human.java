package Films;

public abstract class Human {
    private final String name;
    private int age;
    private final int startAge;
    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.startAge = age;
    }

    public void getOlder() {
        this.age++;
        System.out.println("I got older!");
    }

    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }

    public int getWorkingAge() {
        return this.age - this.startAge;
    }

    public abstract String myProfession();
}
