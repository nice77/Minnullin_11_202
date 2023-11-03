package com.example.semester.servlets.main_page;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Company;
import com.example.semester.models.User;
import com.example.semester.config.FreemarkerConfig;
import com.example.semester.utils.Service;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


@WebServlet(value="/settings")
@MultipartConfig
public class SettingsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("path", "/settings");
        root.put("userType", req.getSession().getAttribute("userType").toString());

        if (req.getSession().getAttribute("userType").equals("user")) {
            User user = (new UserDAO()).getAll()
                    .stream()
                    .filter(u -> u.getEmail().equals(req.getSession().getAttribute("user")))
                    .findFirst().get();
            root.put("user", user);
        }
        else {
            Company company = (new CompanyDAO()).getAll()
                    .stream()
                    .filter(c -> c.getEmail().equals(req.getSession().getAttribute("user")))
                    .findFirst().get();
            root.put("user", company);
        }

        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/settings-main.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class<?> cls = Class.forName("com.example.semester.models." + Service.capitalize(req.getSession().getAttribute("userType").toString()));
            Class<?> daoObjectClass =
                    Class.forName("com.example.semester.DAO."
                            + Service.capitalize(req.getSession().getAttribute("userType").toString()) + "DAO");
            Object daoObject = daoObjectClass.newInstance();
            Object userObject = daoObjectClass.getDeclaredMethod("getByEmail", String.class).invoke(daoObject, req.getSession().getAttribute("user"));

            Service.setObjectFields(cls, req, userObject);

            Method updateMethod = daoObjectClass.getDeclaredMethod("update", userObject.getClass());
            updateMethod.invoke(daoObject, userObject);

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("./settings");
    }
}
