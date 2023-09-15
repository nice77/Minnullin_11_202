package com.example.demo;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>" +
                "<head>" +
                "<link rel='stylesheet' href='./style.css'>" +
                "</head>" +
                "<body>");
        out.println("<div class='main-page'>");
        out.println("<div class='form'>");
        out.println("<h1>Tasks</h1>");
        out.println("<a href='./sayhi'>SayHi</a>");
        out.println("<a href='./calc'>Calculator</a>");
        out.println("</div>" +
                "<img class='rat' src='https://media.tenor.com/tQflxEYnim8AAAAd/hotline-miami-rat-spin.gif' alt='*spinning rat*'>" +
                "</div>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}