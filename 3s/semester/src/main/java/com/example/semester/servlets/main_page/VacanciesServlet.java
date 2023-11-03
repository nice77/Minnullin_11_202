package com.example.semester.servlets.main_page;

import com.example.semester.DAO.*;
import com.example.semester.config.FreemarkerConfig;
import com.example.semester.database.DB;
import com.example.semester.models.Company;
import com.example.semester.models.Post;
import com.example.semester.models.User;
import com.example.semester.models.Vacancy;
import com.example.semester.utils.Service;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(value="/vacancies")
public class VacanciesServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }


    /*
        template payload
        path -> "/vacancies"
            vacanciesMapList -> to display list of vacancies
            currentVacancyObj -> to display current vacancy card
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("path", "/vacancies");

//        if (req.getParameter("cid") != null) {
//            if (req.getSession().getAttribute("userType").equals("company")) {
//                VacancyDAO vacancyDAO = new VacancyDAO();
//                Vacancy currentVacancy = vacancyDAO.get(Integer.parseInt(req.getParameter("cid")));
//                Company vacancyCompany = (new CompanyDAO()).get(currentVacancy.getCompanyId());
//                root.put("currentCardObj", new Object[] {currentVacancy, vacancyCompany});
//                System.out.println("in company usertype branch");
//                root.put("userType", "company");
//            }
//            else if (req.getSession().getAttribute("userType").equals("user")) {
//                PostDAO postDAO = new PostDAO();
//                Post currentPost = postDAO.get(Integer.parseInt(req.getParameter("cid")));
//                User postUser = (new UserDAO()).get(currentPost.getUserId());
//                root.put("currentCardObj", new Object[] {currentPost, postUser});
//                System.out.println("in user usertype branch");
//                root.put("userType", "user");
//            }
//        }

        if (req.getParameter("vid") != null) {
            VacancyDAO vacancyDAO = new VacancyDAO();
            Vacancy currentVacancy = vacancyDAO.get(Integer.parseInt(req.getParameter("vid")));
            Company vacancyCompany = (new CompanyDAO()).get(currentVacancy.getCompanyId());
            root.put("currentCardObj", new Object[] {currentVacancy, vacancyCompany});
            System.out.println("in company usertype branch");
            root.put("userType", "company");
        }
        else if (req.getParameter("pid") != null) {
            PostDAO postDAO = new PostDAO();
            Post currentPost = postDAO.get(Integer.parseInt(req.getParameter("pid")));
            User postUser = (new UserDAO()).get(currentPost.getUserId());
            root.put("currentCardObj", new Object[] {currentPost, postUser});
            System.out.println("in user usertype branch");
            root.put("userType", "user");
        }

        else {
            String query = "select vacancies.* from vacancies" +
                    " join subs on vacancies.id = subs.vacancy_id where subs.user_id = " +
                    (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();

            List<Map<String, String>> data = Service.executeQuery(query);
            root.put("vacanciesMapList", data);
        }

        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/vacancies.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
