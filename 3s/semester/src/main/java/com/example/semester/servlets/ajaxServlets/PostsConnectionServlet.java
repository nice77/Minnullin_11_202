package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.CommentDAO;
import com.example.semester.DAO.PostDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Comment;
import com.example.semester.models.Post;
import com.example.semester.utils.Service;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(value="/postsConnection")
public class PostsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int offset = Integer.parseInt(req.getParameter("offset"));
        int currentUserId = (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
        String query = "select posts.*" +
        " from posts\n" +
        "join users on posts.user_id = users.id\n" +
        "join follows on users.id = follows.author\n" +
        "where follows.follower = " + currentUserId + "\n"+
        "order by posts.id desc limit 10 offset " + offset;
        List<Map<String, String>> out = Service.executeQuery(query);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write((new Gson()).toJson(out));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Post post = new Post();
        PostDAO postDAO = new PostDAO();
        post.setId(Integer.parseInt(req.getParameter("postId")));

        CommentDAO commentDAO = new CommentDAO();
        List<Comment> comments = commentDAO
                .getAll()
                .stream()
                .filter(c -> c.getPostId() == post.getId())
                .collect(Collectors.toList());
        for (Comment comment : comments) {
            commentDAO.delete(comment);
        }

        postDAO.delete(post);
    }
}
