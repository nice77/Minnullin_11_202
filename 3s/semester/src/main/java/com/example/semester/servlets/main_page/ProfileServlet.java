package com.example.semester.servlets.main_page;

import com.example.semester.DAO.FollowDAO;
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
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        root.put("path", "/profile");
        root.put("userType", "user");
        User user;
        UserDAO userDAO = new UserDAO();
        if (req.getParameter("userId") == null) {
            user = userDAO.getByEmail(req.getSession().getAttribute("user").toString());
            root.put("currentUser", true);
        }
        else {
            user = (new UserDAO()).get(Integer.parseInt(req.getParameter("userId")));
            if (req.getSession().getAttribute("userType").equals("user")
                    && user.getId() == userDAO.getByEmail(req.getSession().getAttribute("user").toString()).getId()) {
                root.put("currentUser", true);
            }
            else {
                int otherUserId = Integer.parseInt(req.getParameter("userId"));
                int currentUserId = (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
                boolean isSubscribed = (new FollowDAO()).isSubscribed(currentUserId, otherUserId);
                System.out.println("isSubscribed: " + isSubscribed + "\ncurrentUser: " + currentUserId + "\notherUser: " + otherUserId);
                root.put("isSubscribed", isSubscribed);
                root.put("currentUser", false);
            }
        }
        root.put("user", user);
        root.put("posts", (new PostDAO()).getAll()
                .stream()
                .filter(p -> p.getUserId() == user.getId())
                .collect(Collectors.toList()));
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/profile.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
