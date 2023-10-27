package com.example.semester;

import com.example.semester.DAO.*;

public class Main {
    public static void main(String[] args) {
        CompanyDAO companyDAO = new CompanyDAO();
        UserDAO userDAO = new UserDAO();
        PostDAO postDAO = new PostDAO();
        CommentDAO commentDAO = new CommentDAO();
        VacancyDAO vacancyDAO = new VacancyDAO();
        // есть записи - запускается метод transfer
        // запускается transfer - ищется Service, которого нет =/
        System.out.println(postDAO.getAll()); // ok
        System.out.println(commentDAO.getAll()); // ok
        System.out.println(vacancyDAO.getAll()); // ok
        System.out.println(userDAO.getAll()); // !ok
        System.out.println(companyDAO.getAll()); // !ok
    }
}
