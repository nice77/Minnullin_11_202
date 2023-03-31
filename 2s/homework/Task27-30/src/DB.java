import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class DB {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Subscription> subs = new ArrayList<>();
    private int userId, subId;
    private static DB instance;
    private DB(String usersFileName, String subsFileName) {
        try {
            this.userId = 0;
            Scanner scFile = new Scanner(new File(usersFileName));
            String[] info;
            while (scFile.hasNext()) {
                info = scFile.nextLine().split(" ");
                this.users.add(new User(this.userId, info[0], Integer.parseInt(info[1])));
                this.userId++;
            }

            scFile = new Scanner(new File(subsFileName));
            this.subId = 0;
            while (scFile.hasNext()) {
                info = scFile.nextLine().split(" ");
                this.subs.add(new Subscription(this.subId, this.getUser(Integer.parseInt(info[0])), this.getUser(Integer.parseInt(info[1]))));
                this.subId++;
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static DB getInstance(String usersFileName, String subsFileName) {
        if (instance == null) {
            try {
                instance = new DB(usersFileName, subsFileName);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public User getUser(int id) {
        return this.users.get(id);
    }

    public ArrayList<Subscription> getSubs() {
        return this.subs;
    }
}
