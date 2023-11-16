package com.example.semester.filters;

import com.example.semester.DAO.UserDAO;
import com.example.semester.models.User;
import com.example.semester.utils.PasswordProcessor;
import com.example.semester.utils.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class MainFilter extends HttpFilter {
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("MainFilter called");
        if (!(req.getCookies() == null)) {
            restoreSession(req, res);
        }

        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect("./auth");
            return;
        }
        chain.doFilter(req, res);
    }

    private void restoreSession(HttpServletRequest req, HttpServletResponse res) {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("rememberId") && !cookie.getValue().isEmpty()) {
                String email = cookie.getValue().split("!")[0];
                String password = cookie.getValue().split("!")[1];
                String userType = cookie.getValue().split("!")[2];
                System.out.println("UserType: " + userType);
                if (userType.equals("company") && !PasswordProcessor.checkCompanyCredentials(email, password) ||
                        userType.equals("user") && !PasswordProcessor.checkUserCredentials(email, password)) {
                    Cookie c = new Cookie("rememberId", null);
                    c.setMaxAge(0);
                    res.addCookie(c);
                    try {
                        res.sendRedirect("./auth");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                req.getSession().setAttribute("userType", (userType.equals("user")) ? "user" : "company");
                System.out.println("Set req userType: " + req.getSession().getAttribute("userType"));
                req.getSession().setAttribute("user", email);
            }
        }
    }
}
