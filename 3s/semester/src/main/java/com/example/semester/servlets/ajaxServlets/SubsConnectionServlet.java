package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.SubDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Sub;
import com.example.semester.utils.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/subsConnection")
public class SubsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubDAO subDAO = new SubDAO();
        Sub sub = new Sub();
        if (req.getParameter("toRemove") != null) {
            System.out.println("in remove branch");
            (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString());
            String rowId =
                    Service.executeQuery("select * from subs where vacancy_id = " + req.getParameter("vacancyId") +
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
