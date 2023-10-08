package com.example.task;

import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class VotingServlet extends HttpServlet {
    public void init() {
        FreemarkerConfig.setServletContext(this.getServletContext());
    }

    private void renderTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Template tmpl = FreemarkerConfig.getCfg().getTemplate("vote.ftl");
            response.setContentType("text/html");
            Map<String, Boolean> root = new HashMap<>();
            root.put("user", Service.checkVote((String) request.getSession().getAttribute("user")));
            tmpl.process(root, response.getWriter());
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        renderTemplate(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login"), userPassword = req.getParameter("password");

        // do params exist?
        System.out.println("Found parameter vote: " + req.getParameter("vote"));
        if (req.getParameter("vote") == null) {
            // no session => render template and add user
//            if (Service.checkCredentials(userLogin, userPassword)) {
                req.getSession().setAttribute("user", userLogin);
//            }
        }
        else if (req.getParameter("vote") != null) {
            // session is active
            // check if user voted or not
            Service.addVote((String) req.getSession().getAttribute("user"));
        }
        renderTemplate(req, resp);
    }

    public void destroy() {
    }
}
