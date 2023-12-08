package client.view.fightScreen;

import client.assets.characters.Characters;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Player {
    IntegerProperty hp;
    String name;
    int pos;
    Characters character;

    public Player(String name, int pos, Characters character) {
        this.hp = new SimpleIntegerProperty(100);
        this.name = name;
        this.pos = pos;
        this.character = character;
    }

    public int getHp() {
        return hp.get();
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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Characters getCharacter() {
        return character;
    }

    public void setCharacter(Characters character) {
        this.character = character;
    }
}
