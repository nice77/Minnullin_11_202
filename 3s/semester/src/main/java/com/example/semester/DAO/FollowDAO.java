package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Follow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FollowDAO extends AbstractDAO<Follow> {
    public FollowDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<Follow> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from follows");
            LinkedList<Follow> follows = new LinkedList<>();
            while (rs.next()) {
                follows.add((Follow) transfer(rs));
            }
            return follows;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isSubscribed(int currentUserId, int otherUserId) {
        try (Statement s = this.database.getConnection().createStatement()) {
            String query = "select count(*) from follows where author = " + otherUserId + " and follower = " + currentUserId;
            ResultSet rs = s.executeQuery(query);
            rs.next();
            return Integer.parseInt(rs.getString(1)) == 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
