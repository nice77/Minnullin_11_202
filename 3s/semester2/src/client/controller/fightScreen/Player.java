package client.controller.fightScreen;

import client.assets.characters.Characters;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    private IntegerProperty hp;
    private String name;
    private DoubleProperty pos;
    private Characters character;
    private States state;
    private static final double speed = 5.0;
    private static final int damage = 10;

    public Player(String name, double pos, Characters character) {
        this.hp = new SimpleIntegerProperty(100);
        this.name = name;
        this.pos = new SimpleDoubleProperty(pos);
        this.character = character;
        this.state = States.IDLE;
    }

    public void hit(Player other) {
        other.setHp(other.getHp() - damage);
    }

    public void moveInjured(boolean toRight) {
        this.pos.set(this.pos.get() + ((toRight) ? 4 * speed : 4 * (-speed)));
    }

    public void move(boolean toRight) {
        this.pos.set(this.pos.get() + ((toRight) ? speed : -speed));
    }

    public int getHp() {
        return hp.get();
    }

    public IntegerProperty getHpProperty() {
        return hp;
    }

    public IntegerProperty hpProperty() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubleProperty getPos() {
        return pos;
    }

    public void setPos(double pos) {
        this.pos.setValue(pos);
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
}
