package com.example.semester.servlets.entry;

import com.example.semester.config.FreemarkerConfig;
import com.example.semester.utils.PasswordProcessor;
import com.example.semester.utils.Service;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(value="/auth")
public class AuthorisationServlet extends HttpServlet {

    @Override
    public void init() {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> root = new HashMap<>();
        root.put("path", "/auth");
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./entry/entry.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean userEntry = req.getParameter("company") == null;
        boolean rememberMe = req.getParameter("remember-me") != null;

        if (userEntry) {
            if (PasswordProcessor.checkUserCredentials(email, password)) {
                req.getSession().setAttribute("userType", "user");
                req.getSession().setAttribute("user", email);
                setCookie(req, resp, rememberMe, password);
                resp.sendRedirect("./profile");
            }
        }
        else if (PasswordProcessor.checkCompanyCredentials(email, password)) {
            System.out.println("Got right company credentials");
            req.getSession().setAttribute("userType", "company");
            req.getSession().setAttribute("user", email);
            setCookie(req, resp, rememberMe, password);
            resp.sendRedirect("./company");
        }
        else {
            resp.sendRedirect("./auth");
        }
    }

    private void setCookie(HttpServletRequest req, HttpServletResponse resp, boolean rememberMe, String password) {
        if (rememberMe) {
            Cookie cookie = new Cookie("rememberId", req.getSession().getAttribute("user") + "!" + password + "!" + req.getSession().getAttribute("userType"));
            cookie.setMaxAge(60 * 60 * 24 * 365); // time - needs to be thrown to resources!
            resp.addCookie(cookie);
        }
        else {
            Cookie cookie = new Cookie("rememberId", "");
            cookie.setMaxAge(0); // time - needs to be thrown to resources!
            resp.addCookie(cookie);
        }
    }
}
