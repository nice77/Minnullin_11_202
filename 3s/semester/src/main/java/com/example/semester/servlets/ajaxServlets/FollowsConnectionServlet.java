package com.example.semester.servlets.ajaxServlets;

import com.example.semester.DAO.FollowDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.models.Follow;
import com.example.semester.models.User;
import com.example.semester.utils.StorageService;
import com.example.semester.utils.UserRequestTypes;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value="/followsConnection")
public class FollowsConnectionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usersRequestType = req.getParameter("usersRequestType");
        Integer offset = Integer.valueOf(req.getParameter("offset"));
        List<User> out = null;

        /*
        * главный вопрос в том, как мы получаем данные:
        * выгружаем пользователей как экземпляры классов
        * или проводим запрос через sql и выгружаем всё в список словарей
        */
        if (usersRequestType.equals(UserRequestTypes.UNSUB.getType())) {
            String username = req.getParameter("username");
            String query =
                    "concat(name, ' ', surname, ' ', patronymic) like '%" + username + "%' or\n" +
                    "concat(name, ' ', patronymic, ' ', surname) like '%" + username + "%' or \n" +
                    "concat(surname, ' ', name, ' ', patronymic) like '%" + username + "%' or \n" +
                    "concat(surname, ' ', patronymic, ' ', name) like '%" + username + "%' or \n" +
                    "concat(patronymic, ' ', name, ' ', surname) like '%" + username + "%' or \n" +
                    "concat(patronymic, ' ', surname, ' ', name) like '%" + username + "%'";
            out = username.isEmpty() ? new ArrayList<>() : (new UserDAO()).getSpecific(query);
        }
        else if (usersRequestType.equals(UserRequestTypes.FOLLOWERS.getType())) {
            out = StorageService.getCurrentUserFollowers(req.getSession().getAttribute("user").toString(), offset);
        }
        else if (usersRequestType.equals(UserRequestTypes.AUTHORS.getType())) {
            out = StorageService.getCurrentUserAuthors(req.getSession().getAttribute("user").toString(), offset);
        }

        resp.getWriter().write((new Gson()).toJson(out));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int otherUserId = Integer.parseInt(req.getParameter("userId"));
        int currentUserId = (new UserDAO()).getByEmail(req.getSession().getAttribute("user").toString()).getId();
        boolean isSubscribed = Boolean.parseBoolean(req.getParameter("isSubscribed"));

        System.out.println("Got isSubscribed: " + isSubscribed);
        FollowDAO followDAO = new FollowDAO();
        Follow follow = new Follow(-1, otherUserId, currentUserId);
        if (isSubscribed) {
            follow.setId(followDAO.getAll()
                    .stream()
                    .filter(f -> f.getAuthor() == otherUserId && f.getFollower() == currentUserId)
                    .findFirst()
                    .get()
                    .getId()
            );
            followDAO.delete(follow);
        }
        else {
            System.out.println("Adding a follow");
            followDAO.add(follow);
        }

    }
}
