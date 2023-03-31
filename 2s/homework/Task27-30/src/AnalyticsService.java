import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalyticsService {
    public static List<String> getLastNamesBeforeYear(DB db, int year) {
        return db.getUsers().stream().filter(u -> u.getYear() < year)
                .map(User::getSurname)
                .collect(Collectors.toList());
    }

    public static List<String> getLastNamesBeforeYearClassic(DB db, int year) {
        ArrayList<String> filtered = new ArrayList<>();
        for (User user : db.getUsers()) {
            if (user.getYear() < year) {
                filtered.add(user.getSurname());
            }
        }
        return filtered;
    }
    
    public static int getNumberByYear(DB db, int year) {
        return (int) db.getUsers().stream().filter(u -> u.getYear() == year).count();
    }

    public static int getNumberByYearClassical(DB db, int year) {
        int total = 0;
        for (User user : db.getUsers()) {
            if (user.getYear() == year) {
                total++;
            }
        }
        return total;
    }

    public static List<String> getSubscriptionsNames(DB db, int userId) {
        return db.getSubs().stream()
                .filter(u -> u.getSubscriber().getId() == userId)
                .map(u -> u.getSubscription().getSurname())
                .collect(Collectors.toList());
    }

    public static List<String> getSubscriptionsNamesClassic(DB db, int userId) {
        List<String> out = new ArrayList<>();
        for(Subscription sub : db.getSubs()) {
            if (sub.getSubscriber().getId() == userId) {
                out.add(sub.getSubscription().getSurname());
            }
        }
        return out;
    }

    public static List<String> getSubscribersName(DB db, int userId) {
        return db.getSubs().stream()
                .filter(u -> u.getSubscription().getId() == userId)
                .map(u -> u.getSubscriber().getSurname())
                .collect(Collectors.toList());
    }

    public static List<String> getSubscribersNameClassic(DB db, int userId) {
        List<String> out = new ArrayList<>();
        for(Subscription sub : db.getSubs()) {
            if (sub.getSubscription().getId() == userId) {
                out.add(sub.getSubscriber().getSurname());
            }
        }
        return out;
    }

    public static List<String> getFriends(DB db, int userId) {
        List<Subscription> subscriptions = db.getSubs().stream().filter(u -> u.getSubscriber().getId() == userId).collect(Collectors.toList());
        List<Subscription> subscribers = db.getSubs().stream().filter(u -> u.getSubscription().getId() == userId).collect(Collectors.toList());
        return subscriptions.stream()
                .filter(u1 -> subscribers.stream()
                        .filter(u2 -> u1.getSubscription().getId() == u2.getSubscriber().getId() && u1.getSubscriber().getId() == u2.getSubscription().getId())
                        .count() != 0)
                .map(u -> u.getSubscription().getSurname())
                .collect(Collectors.toList());
    }

    public static List<String> getFriendsClassic(DB db, int userId) {
        List<Subscription> subscriptions = new ArrayList<>();
        List<Subscription> subscribers = new ArrayList<>();
        for (Subscription sub : db.getSubs()) {
            if (sub.getSubscriber().getId() == userId) {
                subscriptions.add(sub);
            }
            if (sub.getSubscription().getId() == userId) {
                subscribers.add(sub);
            }
        }
        List<String> out = new ArrayList<>();
        for (Subscription sub1 : subscriptions) {
            for (Subscription sub2 : subscribers) {
                if (sub1.getSubscription().getId() == sub2.getSubscriber().getId() &&
                        sub1.getSubscriber().getId() == sub2.getSubscription().getId()) {
                    out.add(sub1.getSubscription().getSurname());
                }
            }
        }
        return out;
    }
}
