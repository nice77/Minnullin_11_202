package Films;

import java.util.Arrays;

public class Film {
    private final String name;
    private final int year;
    private final Producer[] producers;
    private final Actor[] actors;
    private final Operator[] operators;
    public Film(String name, int year, Producer[] producers, Actor[] actors, Operator[] operators) {
        this.name = name;
        this.year = year;
        this.producers = Arrays.copyOf(producers, producers.length);
        this.actors = Arrays.copyOf(actors, actors.length);
        this.operators = Arrays.copyOf(operators, operators.length);
    }
    public Producer[] getProducers() {
        return producers;
    }
    public Actor[] getActors() {
        return actors;
    }
    public Operator[] getOperators() {
        return operators;
    }
    @Override
    public String toString() {
        return "Film name: " + this.name + "; " + "Film release year: " + this.year;
    }
}
