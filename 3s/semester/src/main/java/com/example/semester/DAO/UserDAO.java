package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Company;
import com.example.semester.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<User> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from users");
            LinkedList<User> users = new LinkedList<>();
            while (rs.next()) {
                users.add((User) transfer(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getSpecific(String filter) {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from users where " + filter);
            LinkedList<User> users = new LinkedList<>();
            while (rs.next()) {
                users.add((User) transfer(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(int id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from users where id = ?")) {
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (User) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getByEmail(String email) {
        return this.getAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public String toString() {
        return "userDAO";
    }

    @Override
    public void update(User element) {
        super.update(element);
    }

    @Override
    public void add(User element) {
        super.add(element);
    }

    @Override
    public void delete(User element) {
        super.delete(element);
    }
}
