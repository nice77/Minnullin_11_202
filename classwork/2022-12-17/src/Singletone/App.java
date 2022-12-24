package Singletone;

public class App {
    private static App instance;
    private final String name;
    private final DataBase db;
    private App(String name, DataBase db) {
        this.name = name;
        this.db = db;
    }
    public static App getInstance(String name, DataBase db) {
        if (instance == null) {
            instance = new App(name, db);
        }
        return instance;
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        return "App name: " + this.getName();
    }
}
