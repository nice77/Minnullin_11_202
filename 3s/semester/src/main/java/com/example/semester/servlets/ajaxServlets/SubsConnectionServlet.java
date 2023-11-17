package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.SubDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Sub;
import com.example.semester.utils.StorageService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(value="/subsConnection")
public class SubsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int currentUserId = (new CompanyDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
        resp.setCharacterEncoding("UTF-8");
        String query = "select users.* from users \n" +
                "join subs on users.id = subs.user_id\n" +
                "join vacancies on subs.vacancy_id = vacancies.id\n" +
                "where vacancies.company_id = " + currentUserId;
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(StorageService.executeQuery(query)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubDAO subDAO = new SubDAO();
        Sub sub = new Sub();
        if (req.getParameter("toRemove") != null) {
            (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString());
            String rowId =
                    StorageService.executeQuery("select * from subs where vacancy_id = " + req.getParameter("vacancyId") +
                            " and user_id = " + (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId()).get(0).get("id");
            sub.setId(Integer.parseInt(rowId));
            subDAO.delete(sub);
        }
        else {
            sub.setUserId((new UserDAO()) .getByEmail(req.getSession().getAttribute("user").toString()).getId());
            sub.setVacancyId(Integer.parseInt(req.getParameter("vacancyId")));
            subDAO.add(sub);
        }
    }
}
