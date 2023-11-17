package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.SessionDAO;
import com.example.semester.models.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(value="/exit")
public class CloseSessionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionDAO sessionDAO = new SessionDAO();
        List<Session> sessions = sessionDAO
                .getAll()
                .stream()
                .filter(s -> s.getDevice().equals(req.getHeader("User-agent"))).collect(Collectors.toList());
        if (!sessions.isEmpty()) {
            sessionDAO.delete(sessions.get(0));
        }
        req.getSession().removeAttribute("user");
        req.getSession().removeAttribute("userType");
        Cookie cookie = new Cookie("rememberId", null);
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
