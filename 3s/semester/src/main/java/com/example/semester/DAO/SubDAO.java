package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Sub;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class SubDAO extends AbstractDAO<Sub> {
    public SubDAO() {
        this.database = DB.getInstance();
    }
    @Override
    public List<Sub> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from subs");
            LinkedList<Sub> subs = new LinkedList<>();
            while (rs.next()) {
                subs.add((Sub) transfer(rs));
            }
            return subs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Sub get(int user_id, int vacancy_id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from subs where user_id = ? and vacancy_id = ?")) {
            s.setInt(1, user_id);
            s.setInt(2, vacancy_id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (Sub) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sub> getAllSubsByUser(int user_id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from subs where user_id = ?")) {
            s.setInt(1, user_id);
            ResultSet rs = s.executeQuery();
            List<Sub> subs = new LinkedList<>();
            while (rs.next()) {
                subs.add((Sub) transfer(rs));
            }
            return subs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Sub> getAllSubsByVacancy(int vacancy_id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from subs where vacancy_id = ?")) {
            s.setInt(1, vacancy_id);
            ResultSet rs = s.executeQuery();
            List<Sub> subs = new LinkedList<>();
            while (rs.next()) {
                subs.add((Sub) transfer(rs));
            }
            return subs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
