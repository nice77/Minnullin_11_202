package com.example.task;

import com.example.task.DAO.UserDAO;
import com.example.task.DAO.VoteDAO;
import com.example.task.models.User;
import com.example.task.models.Vote;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class Service {
    public static void addUser(String login, String pass) {
        UserDAO.addUser(new User(login, pass));
    }

    public static boolean checkCredentials(String login, String pass) {
        System.out.println("Checking user credentials inside Service class");
        User found = UserDAO.getUser(login);
        return found != null && found.getPassword().equals(pass);
    }

    public static void print() {
        System.out.println("Users: " + UserDAO.getUsers());
    }

    public static void addVote(String userLogin) {
        System.out.println("User login inside service: " + userLogin);
        VoteDAO.addVote(new Vote(userLogin));
    }

    public static boolean checkVote(String userLogin) {
        return VoteDAO.getVotes()
                .stream().anyMatch(vote -> vote.getLogin().equals(userLogin));
    }
}
