package com.example.semester.utils;

import com.example.semester.DAO.FollowDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.database.DB;
import com.example.semester.models.Follow;
import com.example.semester.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StorageService {
    public static void writeToFile(InputStream is, String path, String name) {
        try {
            OutputStream os = new FileOutputStream(path + name);
            int read;
            while ((read = is.read()) != -1) {
                os.write(read);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Used for setting userObject attributes using java.reflect inside SettingsServlet
    // userObject may be an instance of models.Company or models.User
    public static void setObjectFields(Class<?> cls, HttpServletRequest req, Object userObject) {
        try {
            String resField;
            for (Field field : cls.getDeclaredFields()) {
                if (field.getName().equals("id")) {
                    continue;
                }
                Method m = cls.getDeclaredMethod("set" + StringService.capitalize(field.getName()), String.class);
                Part part = req.getPart(field.getName());
                if (part.getSize() == 0) {
                    continue;
                }

                if (field.getName().equals("avatar")) {
                    String name = (String) cls.getDeclaredMethod("getAvatar").invoke(userObject);
                    String path = "C:\\profileImages\\" + req.getSession().getAttribute("userType") + "\\";
                    if (!name.equals("default.png")) {
                        File fileToDelete = new File(path + name);
                        fileToDelete.delete();
                    }

                    name = (System.nanoTime() + name + System.nanoTime()).hashCode() + ".jpg";
                    StorageService.writeToFile(part.getInputStream(), path, name);
                    resField = name;
                } else {
                    resField = new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                }
                m.invoke(userObject, resField);
            }
        } catch (ServletException | IOException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String, String>> executeQuery(String query) {
        List<Map<String, String>> out = new LinkedList<>();
        try (Statement s = DB.getInstance().getConnection().createStatement()) {
            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> temp = new HashMap<>();
                for (int i = 0; i < rsmt.getColumnCount(); i++) {
                    temp.put(rsmt.getColumnLabel(i + 1), rs.getString(i + 1));
                }
                out.add(temp);
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public static List<User> getCurrentUserFollowers(String userEmail, Integer offset) {
        UserDAO userDAO = new UserDAO();
        int currentUserId = userDAO.getByEmail(userEmail).getId();
        List<Integer> followersId = (new FollowDAO()).getAll()
                .stream()
                .filter(f -> f.getAuthor() == currentUserId)
                .map(Follow::getFollower)
                .collect(Collectors.toList());

        List<User> followers = userDAO.getAll()
                .stream()
                .filter(u -> followersId.contains(u.getId()))
                .collect(Collectors.toList());

        if (offset != null && (offset + 10) < followers.size()) {
            followers = followers.subList(offset, offset + 10);
        }
        return followers;
    }

    public static List<User> getCurrentUserAuthors(String userEmail, Integer offset) {
        UserDAO userDAO = new UserDAO();
        int currentUserId = userDAO.getByEmail(userEmail).getId();

        List<Integer> authorsId = (new FollowDAO()).getAll()
                .stream()
                .filter(f -> f.getFollower() == currentUserId)
                .map(Follow::getAuthor)
                .collect(Collectors.toList());
        List<User> authors = userDAO.getAll()
                .stream()
                .filter(u -> authorsId.contains(u.getId()))
                .collect(Collectors.toList());
        if (offset != null && (offset + 10) < authors.size()) {
            authors = authors.subList(offset, offset + 10);
        }
        return authors;
    }
}
