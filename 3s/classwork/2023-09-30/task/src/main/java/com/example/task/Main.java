package com.example.task;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/webapp";
    private static final String USER = "postgres";
    private static final String PASS = "2580456";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users where users.login = '1'");
            while (rs.next()) {
                System.out.println(rs.getString("password"));
            }
            System.out.println();
            System.out.println("Created statement");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
