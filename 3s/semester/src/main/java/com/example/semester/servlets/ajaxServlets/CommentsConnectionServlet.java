package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.CommentDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value="/commentsConnection")
public class CommentsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDAO commentDAO = new CommentDAO();
        Gson gson = new Gson();
        resp.getWriter().write(gson.toJson(commentDAO.getAll()));
    }
}
