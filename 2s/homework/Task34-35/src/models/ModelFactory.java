package models;

public class ModelFactory {
    public static AbstractModel modelCreator(String name, String[] data) {
        switch (name) {
            case "user":
                return new User(data);
            case "group":
                return new Group(data);
            case "users_groups":
                return new UsersGroups(data);
            case "subscriptions":
                return new Subscriptions(data);
        }
        return null;
    }
}
