package com.example.semester.servlets.main_page;

import com.example.semester.DAO.FollowDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.config.FreemarkerConfig;
import com.example.semester.models.Follow;
import com.example.semester.models.User;
import com.example.semester.utils.Service;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(value="/follows")
public class FollowsServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
//          SQL-based way of extracting needed users
//            String queryFollowers = "select * \n" +
//                    "from follows\n" +
//                    "join users on follows.follower = users.id\n" +
//                    "where follows.author = "
//                    + (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
//
//            List<Map<String, String>> out = Service.executeQuery(queryFollowers);
//            Map<String, Object> root = new HashMap<>();
//            List<User> authors = Service.getCurrentUserAuthors(req.getSession().getAttribute("user").toString(), null);
//            root.put("authors", authors);
            Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/follows.ftl");
            tmpl.process(null, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
