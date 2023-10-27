package com.example.semester.DAO;

import com.example.semester.database.DB;
import com.example.semester.models.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class CompanyDAO extends AbstractDAO<Company> {

    public CompanyDAO() {
        this.database = DB.getInstance();
    }

    @Override
    public List<Company> getAll() {
        try (Statement s = this.database.getConnection().createStatement()) {
            ResultSet rs = s.executeQuery("select * from companies");
            LinkedList<Company> companies = new LinkedList<>();
            while (rs.next()) {
                companies.add((Company) transfer(rs));
            }
            return companies;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Company get(int id) {
        try (PreparedStatement s = this.database.getConnection().prepareStatement("select * from companies where id = ?")) {
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            rs.next();
            return (Company) transfer(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Company getByEmail(String email) {
        return this.getAll().stream().filter(c -> c.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public String toString() {
        return "companyDAO";
    }

    @Override
    public void update(Company element) {
        super.update(element);
    }

    @Override
    public void add(Company element) {
        super.add(element);
    }

    @Override
    public void delete(Company element) {
        super.delete(element);
    }
}
