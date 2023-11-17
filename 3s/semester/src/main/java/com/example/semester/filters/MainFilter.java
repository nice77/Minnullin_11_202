package com.example.semester.filters;

import com.example.semester.utils.CookieService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("MainFilter called");
        if (!(req.getCookies() == null)) {
            CookieService.restoreSession(req, res);
        }

        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("./auth");
            return;
        }
        chain.doFilter(req, res);
    }
}
