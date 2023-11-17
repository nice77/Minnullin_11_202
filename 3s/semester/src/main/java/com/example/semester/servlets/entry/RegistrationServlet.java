package com.example.semester.servlets.entry;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Company;
import com.example.semester.models.User;
import com.example.semester.config.FreemarkerConfig;
import com.example.semester.utils.PasswordProcessor;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value="/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> root = new HashMap<>();
        root.put("path", "/register");
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./entry/entry.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("In doPost; " + req.getParameterMap());
        boolean checkbox = req.getParameter("company") == null;
        if (checkbox) {
            UserDAO userDAO = new UserDAO();
            System.out.println("Name field: " + req.getParameter("name"));
            User user = new User(-1,
                    req.getParameter("name"),
                    "",
                    "",
                    req.getParameter("phone"),
                    req.getParameter("email"),
                    PasswordProcessor.getHashedPassword(req.getParameter("password")),
                    "",
                    "default.png");
            userDAO.add(user);
        }
        else {
            CompanyDAO companyDAO = new CompanyDAO();
            Company company = new Company(-1,
                    req.getParameter("name"),
                    req.getParameter("phone"),
                    req.getParameter("email"),
                    PasswordProcessor.getHashedPassword(req.getParameter("password")),
                    null,
                    "default.png");
            System.out.println(company.getName() + ", " + company.getPhoneNumber() + ", " + company.getEmail() + ", "
                    + company.getHashedPassword() + ", " + company.getAbout());
            companyDAO.add(company);
        }
        resp.sendRedirect("./auth");
    }
}
