package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Vacancy;
import com.example.semester.models.Vacancy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class VacancyDAO extends AbstractDAO<Vacancy> {
    public VacancyDAO() {
        this.database = DB.getInstance();
    }
    @Override
    public List<Vacancy> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from vacancies");
            LinkedList<Vacancy> vacancies = new LinkedList<>();
            while (rs.next()) {
                vacancies.add((Vacancy) transfer(rs));
            }
            return vacancies;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vacancy get(int id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from vacancies where id = ?")) {
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (Vacancy) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
