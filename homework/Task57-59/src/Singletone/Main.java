package Singletone;

public class Main {
    String pathToFile = "C://Users/Wrench/Desktop/MyProject/database.db";
    DataBase db = DataBase.getInstance(pathToFile);
    App app = App.getInstance("Facebook", db);
}
