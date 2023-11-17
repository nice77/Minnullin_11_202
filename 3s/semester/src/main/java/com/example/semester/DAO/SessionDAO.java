package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Comment;
import com.example.semester.models.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SessionDAO extends AbstractDAO<Session> {

    public SessionDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<Session> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from sessions");
            LinkedList<Session> sessions = new LinkedList<>();
            while (rs.next()) {
                sessions.add((Session) transfer(rs));
            }
            return sessions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
