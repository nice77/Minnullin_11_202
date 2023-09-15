package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Hi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        String s = req.getParameter("name");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<head>" +
                "<link rel='stylesheet' href='./style.css'>" +
                "</head>" +
                "<body>" +
                "<div class=main-page>" +
                "<div class='form'><h2>");
        if (!s.isEmpty()) {
            out.println("Hi, " + s + "!");
        }
        else {
            out.println("Hi, Barbie!");
        }
        out.println("</h2><a href='./sayhi'>Back</a>");
        out.println("</div></div></body></html>");
    }
}
