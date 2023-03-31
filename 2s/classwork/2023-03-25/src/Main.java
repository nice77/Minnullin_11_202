public class Main {
    public static void main(String[] args) {
        task01();
        System.out.println("---------");
        task02();
    }

    public static void task01() {
        DB db = DB.getInstance("input.txt", "subscriptions.txt");
        System.out.println("Streams getLastNamesBeforeYear:");
        AnalyticsService.getLastNamesBeforeYear(db, 1990).forEach(System.out::println);
        System.out.println("Classical getLastNamesBeforeYear:");
        AnalyticsService.getLastNamesBeforeYearClassic(db, 1990).forEach(System.out::println);
        System.out.println("Streams getNumberByYear: " + AnalyticsService.getNumberByYear(db, 1969));
        System.out.println("Classical getNumberByYear: " + AnalyticsService.getNumberByYearClassical(db, 1969));
    }

    public static void task02() {
        DB db = DB.getInstance("input.txt", "subscriptions.txt");
        System.out.println("Subscriptions:");
        AnalyticsService.getSubscriptionsNames(db, 0).forEach(System.out::println);
        System.out.println("Subscribers:");
        AnalyticsService.getSubscribersName(db, 0).forEach(System.out::println);
        System.out.println("Friends:");
        AnalyticsService.getFriends(db, 2).forEach(System.out::println);
        System.out.println("---------");
        System.out.println("Subscriptions classic:");
        AnalyticsService.getSubscriptionsNamesClassic(db, 0).forEach(System.out::println);
        System.out.println("Subscribers classic:");
        AnalyticsService.getSubscribersNameClassic(db, 0).forEach(System.out::println);
        System.out.println("Friends classic:");
        AnalyticsService.getFriendsClassic(db, 2).forEach(System.out::println);
    }
}
