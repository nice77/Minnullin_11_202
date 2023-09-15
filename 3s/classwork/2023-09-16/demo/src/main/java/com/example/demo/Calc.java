package com.example.demo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Calc extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String a = "a", b = "b", operation = "operation";
        String uno = request.getParameter(a);
        String dos = request.getParameter(b);
        String op = request.getParameter(operation);
        System.out.println("Operation: " + op);
        out.println("<html>" +
                "<head>" +
                "<link rel='stylesheet' href='./style.css'>" +
                "</head>" +
                "<body><div class='main-page'><form>");
        out.println("<h1>Calculator</h1>");
        out.println("<input name='a' type='number'" + (uno == null ? "" : "value='" + uno + "'") + " placeholder='número uno'>");
        out.println("<input name='b' type='number'" + (dos == null ? "" : "value='" + dos + "'") + " placeholder='número dos'>");
        out.println("<select name='operation'>");

        String lines = "<option>*</option>" +
        "<option>+</option>" +
        "<option>/</option>" +
        "<option>-</option>";
        lines = lines.replace(">" + op, " selected>" + op);

        out.println(lines);
        out.println("</select>");
        out.println("<input type='submit'>");
        if (uno != null && dos != null) {
            out.println("<h2>" + Helper.calculate(Integer.parseInt(uno), Integer.parseInt(dos), op) + "</h2>");
        }
        out.println("<a href='.'>Back</a>");
        out.println("</form></div></body></html>");
    }
}
