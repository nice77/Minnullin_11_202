package com.example.semester.servlets.main_page;


import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.VacancyDAO;
import com.example.semester.models.Company;
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
import java.util.stream.Collectors;

@WebServlet(value="/company")
public class CompanyServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        root.put("path", "/company");
        CompanyDAO companyDAO = new CompanyDAO();
        Company company;


        root.put("userType", "company");
        if (req.getParameter("companyId") == null) {
            company = companyDAO.getByEmail(req.getSession().getAttribute("user").toString());
            root.put("currentUser", true);
        }
        else {
            company = companyDAO.get(Integer.parseInt(req.getParameter("companyId")));
            if (req.getSession().getAttribute("userType").equals("company")
                    && company.getId() == companyDAO.getByEmail(req.getSession().getAttribute("user").toString()).getId()) {
                root.put("currentUser", true);
            }
            else {
                root.put("currentUser", false);
            }
        }

        root.put("user", company);
        VacancyDAO vacancyDAO = new VacancyDAO();
        root.put("posts", vacancyDAO.getAll()
                .stream()
                .filter(v -> v.getCompanyId() == company.getId())
                .collect(Collectors.toList()));
        Template tmpl = FreemarkerConfig.getConfig().getTemplate("./main-page/profile.ftl");
        try {
            tmpl.process(root, resp.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
