package com.example.semester.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VacanciesFollowsFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String currPost = req.getParameter("vid");
        String currVacancy = req.getParameter("pid");
        if (req.getSession().getAttribute("userType").equals("company")
                && currPost == null && currVacancy == null) {
            res.sendRedirect("./profile");
        }
        chain.doFilter(req, res);
    }
}
