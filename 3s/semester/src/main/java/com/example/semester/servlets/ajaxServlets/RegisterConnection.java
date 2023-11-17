package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.UserDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/registerConnection")
public class RegisterConnection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String email = req.getParameter("email");
        boolean isCompany = gson.fromJson(req.getParameter("isCompany"), Boolean.class);
        boolean isCorrect = true;
        if (isCompany) {
            isCorrect = (new CompanyDAO()).getAll().stream().noneMatch(c -> c.getEmail().equals(email));
        }
        else {
            isCorrect = (new UserDAO()).getAll().stream().noneMatch(c -> c.getEmail().equals(email));
        }
        resp.getWriter().write(gson.toJson(isCorrect));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
