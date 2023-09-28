package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("./register.html");
        dispatcher.forward(request, response);
    }

    public void destroy() {
    }
}