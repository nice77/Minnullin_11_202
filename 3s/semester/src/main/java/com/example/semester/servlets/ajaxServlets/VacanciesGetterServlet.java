package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.SubDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.DAO.VacancyDAO;
import com.example.semester.models.Vacancy;
import com.example.semester.utils.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/vacanciesConnection")
public class VacanciesGetterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        String query = "select vacancies.* from vacancies\n" +
                "except select vacancies.* from vacancies\n" +
                "join subs on vacancies.id = subs.vacancy_id\n" +
                "where subs.user_id = " + userDAO.getByEmail(req.getSession().getAttribute("user").toString()).getId();
        System.out.println(query);

        Gson gson = new Gson();
        if (req.getParameter("getAll").equals("true")) {
            String output = gson.toJson(Service.executeQuery(query));
            System.out.println(output);
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().write(output);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int vacancyId = Integer.parseInt(req.getParameter("vacancyId"));
        VacancyDAO vacancyDAO = new VacancyDAO();
        Vacancy toDelete = new Vacancy();
        toDelete.setId(vacancyId);
        vacancyDAO.delete(toDelete);
    }
}
