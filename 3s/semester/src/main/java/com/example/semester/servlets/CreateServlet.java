package com.example.semester.servlets;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.PostDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.DAO.VacancyDAO;
import com.example.semester.config.FreemarkerConfig;
import com.example.semester.models.Post;
import com.example.semester.models.Vacancy;
import com.example.semester.utils.Service;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(value="/create")
@MultipartConfig
public class CreateServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, String> root = new HashMap<>();
        root.put("userType", req.getSession().getAttribute("userType").toString());
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./createPost/create.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getSession().getAttribute("userType").equals("company")) {
                Vacancy vacancy = new Vacancy();
                VacancyDAO vacancyDAO = new VacancyDAO();
                vacancy.setCompanyId((new CompanyDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId());
                String[] partNames = new String[] {"header", "body", "meetingDate", "meetingPlace"};
                for (String s : partNames) {
                    Part part = req.getPart(s);
                    Method m;
                    String resField = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                    System.out.println("Result field: " + resField + "; " + "Name: " + s);
                    if (s.toLowerCase().contains("date")) {
                        DateFormat formatter = new SimpleDateFormat("yy-MM-dd");
                        m = Vacancy.class.getDeclaredMethod("set" + Service.capitalize(s), Date.class);
                        m.invoke(vacancy, formatter.parse(resField));
                    }
                    else {
                        m = Vacancy.class.getDeclaredMethod("set" + Service.capitalize(s), String.class);
                        m.invoke(vacancy, resField);
                    }
                }
                vacancyDAO.add(vacancy);
            }
            else if (req.getSession().getAttribute("userType").equals("user")) {
                Post post = new Post();
                PostDAO postDAO = new PostDAO();
                post.setUserId((new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId());
                post.setCreatingDate(new Date());
                String[] partNames = new String[] {"header", "body"};
                for (String s : partNames) {
                    Part part = req.getPart(s);
                    String resField = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                    System.out.println("Result field: " + resField + "; " + "Name: " + s);
                    Method m = Post.class.getDeclaredMethod("set" + Service.capitalize(s), String.class);
                    m.invoke(post, resField);
                }
                postDAO.add(post);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("./profile");
    }
}