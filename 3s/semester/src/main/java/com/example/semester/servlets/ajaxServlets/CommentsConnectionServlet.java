package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.CommentDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Comment;
import com.example.semester.utils.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(value="/commentsConnection")
public class CommentsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        resp.setCharacterEncoding("utf-8");
        String postId = req.getParameter("data");
        int offset = Integer.parseInt(req.getParameter("offset"));

        String query = "select comments.*, users.name, users.surname from comments " +
                "join users on users.id = comments.user_id " +
                "where post_id = " + postId + " order by id desc limit 10 offset " + offset;

        List<Map<String, String>> out = Service.executeQuery(query);
        resp.getWriter().write(gson.toJson(out));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = new Comment();
        comment.setText(req.getParameter("text"));
        comment.setPostId(Integer.parseInt(req.getParameter("post")));
        comment.setCreateDate(new Date());
        comment.setUserId((new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId());

        CommentDAO commentDAO = new CommentDAO();
        commentDAO.add(comment);
        resp.setCharacterEncoding("utf-8");
        String postId = req.getParameter("post");
        String query = "select comments.*, users.name, users.surname from comments " +
                "join users on users.id = comments.user_id " +
                "where post_id = " + postId + " order by comments.id desc limit 1";

        List<Map<String, String>> out = Service.executeQuery(query);
        resp.getWriter().write((new Gson()).toJson(out));
    }
}
