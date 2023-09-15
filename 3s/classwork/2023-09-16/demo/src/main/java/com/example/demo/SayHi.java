package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SayHi extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<link rel='stylesheet' href='./style.css'>");
        out.println("<div class='main-page'>");
        out.println("<form action='./hi'>");
        out.println("<h1>Say 'Hi!'</h1>");
        out.println("<input type='text' name='name' placeholder='name'>");
        out.println("<input type='submit'>");
        out.println("<a href='.'>Back</a>");
        out.println("</form></div>");
        out.println("</body></html>");
    }
}
