package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Post;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PostDAO extends AbstractDAO<Post> {

    public PostDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<Post> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from posts");
            LinkedList<Post> posts = new LinkedList<>();
            while (rs.next()) {
                posts.add((Post) transfer(rs));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Post get(int id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from posts where id = ?")) {
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (Post) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
