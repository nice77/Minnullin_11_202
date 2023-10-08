package com.example.task;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;

public class AuthServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("Is user active inside AuthServlet: " + request.getSession().getAttribute("user"));
        if (request.getSession().getAttribute("user") == null) {
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("./auth.html");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect(request.getContextPath() + "/vote");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("./auth.html");
        dispatcher.forward(req, resp);
        if (req.getParameter("login") != null) {
            System.out.println("Adding user after registration");
            Service.addUser(req.getParameter("login"), req.getParameter("password"));
            Service.print();
        }
        else {
            System.out.println("Removing user");
            req.getSession().invalidate();
        }
    }

    public void destroy() {
    }
}