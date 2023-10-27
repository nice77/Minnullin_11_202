package com.example.semester.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static DB instance;
    private Connection connection;
    private static final String CONNECTION = "jdbc:postgresql://localhost:5432/semester";
    private static final String USER = "postgres";
    private static final String PASS = "";

    private DB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(CONNECTION, USER, PASS);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DB getInstance() {
        if (instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
