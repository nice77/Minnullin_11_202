package com.example.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Service {
    private static Map<String, String> users = new HashMap<>();
    private static Set<String> usersVotes = new HashSet<>();

    public static void addUser(String login, String pass) {
        if (!users.containsKey(login)) {
            users.put(login, pass);
        }
    }

    public static boolean checkCredentials(String login, String pass) {
        System.out.println("Checking user credentials inside Service class");
        return users.containsKey(login) && users.get(login).equals(pass);
    }

    public static void print() {
        System.out.println("Users: " + users + "\nVoted: " + usersVotes);
    }

    public static void addVote(String userLogin) {
        usersVotes.add(userLogin);
    }

    public static boolean checkVote(String userLogin) {
        return usersVotes.contains(userLogin);
    }
}
