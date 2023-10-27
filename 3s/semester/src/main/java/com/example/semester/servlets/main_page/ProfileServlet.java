package com.example.semester.servlets.main_page;

import com.example.semester.DAO.PostDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.User;
import com.example.semester.config.FreemarkerConfig;
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

@WebServlet(value="/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        User user = (new UserDAO()).getAll()
                .stream()
                .filter(u -> u.getEmail().equals(req.getSession().getAttribute("user")))
                .findFirst().get();
        root.put("path", "/profile");
        root.put("userType", req.getSession().getAttribute("userType"));
        root.put("user", user);
        System.out.println();
        root.put("posts", (new PostDAO()).getAll());
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/main-page.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
