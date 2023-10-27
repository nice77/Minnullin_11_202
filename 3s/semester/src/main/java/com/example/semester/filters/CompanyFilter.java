package com.example.semester.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CompanyFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("Company filter called");
        if (req.getSession().getAttribute("userType").equals("user")) {
            res.sendRedirect("./profile");
            return;
        }
        chain.doFilter(req, res);
    }
}
