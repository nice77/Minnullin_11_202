package com.example.demo;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class VotingServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher;
        if (request.getSession().getAttribute("user") == null) {
            dispatcher = request.getRequestDispatcher("./vote_hidden.html");
        }
        else {
            if (Service.checkVote((String) request.getSession().getAttribute("user"))) {
                dispatcher = request.getRequestDispatcher("./vote_hidden.html");
            }
            else {
                dispatcher = request.getRequestDispatcher("./vote.html");
            }
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userLogin = req.getParameter("login"), userPassword = req.getParameter("password");
        RequestDispatcher dispatcher = req.getRequestDispatcher("./vote.html");

        // if we're already have an active session
        if (req.getSession().getAttribute("user") != null) {
            System.out.println("Session isn't empty");
            String authLogin = (String) req.getSession().getAttribute("user");
            if (req.getParameter("vote") != null) {
                Service.addVote((String) req.getSession().getAttribute("user"));
                Service.print();
                dispatcher = req.getRequestDispatcher("./vote_done.html");
            } else if (req.getParameter("vote") == null && Service.checkVote(authLogin)) {
                dispatcher = req.getRequestDispatcher("./vote_done.html");
            }
            dispatcher.forward(req, resp);
        }

        // if the credentials we've entered are correct we go to /vote
        else if (Service.checkCredentials(userLogin, userPassword)) {
            System.out.println("Session was empty, added user: " + userLogin);
            req.getSession().setAttribute("user", userLogin);
            if (Service.checkVote(userLogin)) {
                dispatcher = req.getRequestDispatcher("./vote_done.html");
            }
            dispatcher.forward(req, resp);
        }

        else {
            resp.sendRedirect(req.getContextPath() + "/auth");
        }
    }

    public void destroy() {
    }
}