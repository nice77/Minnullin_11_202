package Singletone;

public class DataBase {
    private final String pathToFile;
    private static DataBase instance;
    private DataBase(String pathToFile) {
        this.pathToFile = pathToFile;
    }
    public static DataBase getInstance(String pathToFile) {
        if (instance == null) {
            instance = new DataBase(pathToFile);
        }
        return instance;
    }
}
