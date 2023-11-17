package com.example.semester.utils;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.SessionDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Session;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieService {
    public static void setCookie(HttpServletRequest req, HttpServletResponse resp, boolean rememberMe, String userType) {
        if (rememberMe) {
            int currentUserId;
            if (userType.equals("user")) {
                currentUserId = (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
            }
            else {
                currentUserId = (new CompanyDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
            }

            SessionDAO sessionDAO = new SessionDAO();
            boolean sessionExists = sessionDAO.getAll()
                    .stream()
                    .anyMatch(s -> s.getDevice().equals(req.getHeader("User-agent")));
            System.out.println("Session exists: " + sessionExists);
            if (sessionExists) {
                Session foundSession = sessionDAO.getAll()
                        .stream()
                        .filter(s -> s.getDevice().equals(req.getHeader("User-agent")))
                        .findFirst()
                        .get();
                foundSession.setToken(req.getSession().getId());
                foundSession.setUserType(userType);
                foundSession.setUserId(currentUserId);
                sessionDAO.update(foundSession);
            }
            else {
                Session session = new Session();
                session.setUserId(currentUserId);
                session.setDevice(req.getHeader("User-agent"));
                session.setToken(req.getSession().getId());
                session.setUserType(userType);
                sessionDAO.add(session);
            }

            String cookieValue =
                    req.getSession().getAttribute("user") + "!"
                    + req.getSession().getId() + "!"
                    + req.getSession().getAttribute("userType");
            Cookie cookie = new Cookie("rememberId",  cookieValue);
            cookie.setMaxAge(60 * 60 * 24 * 365);
            resp.addCookie(cookie);
        }
        else {
            Cookie cookie = new Cookie("rememberId", "");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
        }
    }

    public static void restoreSession(HttpServletRequest req, HttpServletResponse res) {
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("rememberId") && !cookie.getValue().isEmpty()) {
                String email = cookie.getValue().split("!")[0];
                String token = cookie.getValue().split("!")[1];
                String userType = cookie.getValue().split("!")[2];
                int userId;
                if (userType.equals("user")) {
                    userId = (new UserDAO()).getByEmail(email).getId();
                }
                else {
                    userId = (new CompanyDAO()).getByEmail(email).getId();
                }

                System.out.println("userId: " + userId + "; token: " + token + "; userType: " + userType);
                boolean result = isTokenValid(userId, token, userType);
                if (result) {
                    req.getSession().setAttribute("userType", (userType.equals("user")) ? "user" : "company");
                    req.getSession().setAttribute("user", email);
                }
//                else {
//                    try {
//                        res.sendRedirect("./auth");
//                        return;
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
            }
        }
    }

    public static boolean isTokenValid(int id, String token, String userType) {
        SessionDAO sessionDAO = new SessionDAO();
        return sessionDAO.getAll()
                .stream()
                .anyMatch(s -> s.getUserId() == id && s.getToken().equals(token) && s.getUserType().equals(userType));
    }
}
