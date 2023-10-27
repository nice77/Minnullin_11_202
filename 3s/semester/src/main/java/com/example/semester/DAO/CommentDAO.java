package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Comment;
import com.example.semester.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CommentDAO extends AbstractDAO<Comment> {

    public CommentDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<Comment> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from comments");
            LinkedList<Comment> comments = new LinkedList<>();
            while (rs.next()) {
                comments.add((Comment) transfer(rs));
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Comment get(int id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from comments where id = ?")) {
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (Comment) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }
}
