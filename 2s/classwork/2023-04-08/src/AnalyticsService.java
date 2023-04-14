import friendGraph.Graph;
import models.*;
import modelsTables.AbstractTable;
import modelsTables.UserTable;
import modelsTables.UsersGroupsTable;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsService {
    public static List<User> getUsers(DB db, Group group) {
        List<User> temp = db.getData().get("user").getTable().stream()
                .map(r -> (User) r).collect(Collectors.toList());
        return db.getData().get("users_groups")
                .getTable().stream()
                .map(r -> (UsersGroups) r)
                .filter(r -> r.getGroupId() == group.getId())
                .map(r -> (User) db.getData().get("user").getRowById(r.getUserId()))
                .collect(Collectors.toList());
    }

    public static List<User> getUsers(DB db, int groupId) {
        List<User> temp = db.getData().get("user").getTable().stream()
                .map(r -> (User) r).collect(Collectors.toList());
        return db.getData().get("users_groups").getTable().stream()
                .map(r -> (UsersGroups) r)
                .filter(r -> r.getGroupId() == groupId)
                .map(r -> (User) db.getData().get("user").getRowById(r.getUserId()))
                .collect(Collectors.toList());
    }

    public static List<User> getUsersOfSameCity(DB db, Group group) {
        List<User> temp = getUsers(db, group);
        return temp.stream().filter(r -> r.getCity().equals(group.getCity())).collect(Collectors.toList());
    }

    public static int getNumberOfUsers(DB db, Group group) {
        return getUsers(db, group).size();
    }

    public static int getNumberOfUsers(DB db, int group) {
        return getUsers(db, group).size();
    }

    public static void friendshipLevelVerOne(DB db, Group group) {
        List<User> users = getUsers(db, group);
        List<Subscriptions> subscriptions = db.getData().get("subscriptions")
                .getTable()
                .stream()
                .map(r -> (Subscriptions) r)
                .filter(r -> users.stream().anyMatch(u -> r.getWho_id() == u.getId()))
                .filter(r -> users.stream().anyMatch(u -> r.getOn_whom_id() == u.getId()))
                .collect(Collectors.toList());
        List<Subscriptions> finalSubscriptions = subscriptions.stream()
                .filter(r1 -> subscriptions.stream()
                        .anyMatch(r2 -> r1.getWho_id() == r2.getOn_whom_id() && r1.getOn_whom_id() == r2.getWho_id()))
                .collect(Collectors.toList());
        int groupSize = users.size();
        List<Integer> counted = new LinkedList<>();
        for (Subscriptions sub : finalSubscriptions) {
            if (!counted.contains(sub.getWho_id())) {
                counted.add(sub.getWho_id());
            }
            if (!counted.contains(sub.getOn_whom_id())) {
                counted.add(sub.getOn_whom_id());
            }
        }
        int subSize = counted.size();
        System.out.println((int) ((subSize / (double) groupSize) * 100));
    }

    public static List<User> getFriends(DB db, User user) {
        List<User> users = db.getData().get("user").getTable()
                .stream()
                .map(r -> (User) r)
                .collect(Collectors.toList());
        List<Subscriptions> subscriptions = db.getData().get("subscriptions").getTable()
                .stream()
                .map(r -> (Subscriptions) r)
                .filter(r -> r.getWho_id() == user.getId() || r.getOn_whom_id() == user.getId())
                .collect(Collectors.toList());
        List<Subscriptions> finalSubscriptions = subscriptions.stream()
                .filter(r1 -> subscriptions.stream()
                        .anyMatch(r2 -> r1.getWho_id() == r2.getOn_whom_id() && r1.getOn_whom_id() == r2.getWho_id()))
                .collect(Collectors.toList());
        AbstractTable userTable = db.getData().get("user");
        List<User> finalUsers = finalSubscriptions.stream()
                .filter(r -> r.getWho_id() == user.getId())
                .map(r -> (User) userTable.getRowById(r.getOn_whom_id()))
                .collect(Collectors.toList());
        return finalUsers;
    }

    public static void friendshipLevelVerTwo(DB db, Group group) {
        List<User> groupUsers = getUsers(db, group);
        Graph graph = new Graph();
        for (User user : groupUsers) {
            graph.add(user, getFriends(db, user).stream()
                    .filter(u -> groupContains(db, u, group))
                    .collect(Collectors.toList()));
        }
        System.out.println(graph.BFS());
    }

    public static boolean groupContains(DB db, User user, Group group) {
        UsersGroupsTable usersGroups = (UsersGroupsTable) db.getData().get("users_groups");
        return usersGroups.groupContains(user, group);
    }
}
