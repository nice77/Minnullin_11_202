import models.AbstractModel;
import models.Group;
import models.User;

public class Main {
    public static void main(String[] args) {
        String[] dbFiles = new String[] {"user.txt", "group.txt", "users_groups.txt", "subscriptions.txt"};
        DB db = DB.getInstance(dbFiles);

        for (AbstractModel group : db.getData().get("group").getTable()) {
            System.out.println(group + ", id: " + group.getId() + "; has "  + AnalyticsService.getNumberOfUsers(db, group.getId()) + " users");
            System.out.println("Users in this group:");
            System.out.println(AnalyticsService.getUsers(db, group.getId()));
            // Получение друзей пользователя
//            System.out.println("Friends of this group");
//            System.out.println(AnalyticsService.getFriends(db, (User) db.getData().get("user").getRowById(group.getId())));
            System.out.println("Users that have same city");
            System.out.println(AnalyticsService.getUsersOfSameCity(db, (Group) db.getData().get("group").getRowById(group.getId())));
            System.out.println("Friendship level by non-graphs");
            AnalyticsService.friendshipLevelVerOne(db, (Group) db.getData().get("group").getRowById(group.getId()));
            System.out.println("Friendship level by graphs");
            AnalyticsService.friendshipLevelVerTwo(db, (Group) db.getData().get("group").getRowById(group.getId()));
            System.out.println("\n" + "---------" + "\n");
        }
    }
}
