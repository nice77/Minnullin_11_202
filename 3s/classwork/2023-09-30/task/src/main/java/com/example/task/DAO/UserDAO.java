package com.example.task.DAO;

import com.example.task.DB;
import com.example.task.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDAO {
    public static List<User> getUsers() {
        DB database = DB.getInstance();
        try (Statement stmt = database.getConn().createStatement()){
            ResultSet rs = stmt.executeQuery("select * from users");
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                userList.add(new User(rs.getString("login"), rs.getString("password")));
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(User user) {
        DB database = DB.getInstance();
        try (PreparedStatement stmt = database.getConn().prepareStatement("insert into users (login, password) values (?, ?)")) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String login) {
        List<User> userList = getUsers();
        return userList.stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }
}
