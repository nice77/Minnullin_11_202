package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AuthServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./auth.html");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("./auth.html");
        dispatcher.forward(req, resp);
        if (req.getParameter("login") != null) {
            Service.addUser(req.getParameter("login"), req.getParameter("password"));
            Service.print();
        }
        else {
            req.getSession().removeAttribute("user");
            System.out.println("Authorised user now: " + req.getSession().getAttribute("user"));
        }
    }

    public void destroy() {
    }
}