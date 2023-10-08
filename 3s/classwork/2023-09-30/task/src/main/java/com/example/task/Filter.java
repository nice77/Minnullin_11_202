package com.example.task;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Filter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Object userAttr = req.getSession().getAttribute("user");
        String userLogin = req.getParameter("login"), userPassword = req.getParameter("password");
        System.out.println("Check credentials inside filter: " + userLogin + "; " + userPassword);
        System.out.println("Check user inside filter: " + userAttr);
        System.out.println("Method inside filter: " + req.getMethod());
        System.out.println("Calling checkCredentials(): " + Service.checkCredentials(userLogin, userPassword));
        if ((userAttr == null && req.getMethod().equals("GET"))
                || (userAttr == null && !Service.checkCredentials(userLogin, userPassword))) {
            System.out.println("Redirect to ./auth");
            res.sendRedirect(req.getContextPath() + "/auth");
        }
        else {
            chain.doFilter(req, res);
        }
    }
}
