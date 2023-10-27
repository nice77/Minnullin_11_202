package com.example.semester.utils;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

public class Service {
    // method getClassName is used to process class name from table name
    public static String getNaym(String name) {
        String out;
        out = name.substring(0, name.length() - 1).replace("ie", "y");
        out = out.substring(0, 1).toUpperCase() + out.substring(1);
        System.out.println("Got: " + out);
        return out;
    }

    // method getAttributeName is used to process class attribute name from table column
    public static String getAttributeName(String name) {
        String out;
        int foundIndexOfSpace = name.indexOf('_');
        out = name.replace(String.valueOf(name.charAt(foundIndexOfSpace)), "");
        out = out.substring(0, foundIndexOfSpace) +
                out.substring(foundIndexOfSpace, foundIndexOfSpace + 1).toUpperCase() +
                out.substring(foundIndexOfSpace + 1);
        return out;
    }

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    //
    public static String getColumnName(String name) {
        for (int i = 0; i < name.length(); i++) {
            char letter = name.charAt(i);
            if (65 <=  letter && letter <= 90) {
                name = name.replace(String.valueOf(name.charAt(i)), "_" + (char) (letter + 32));
                System.out.println("Replaced: " + name);
            }
        }
        return name;
    }

    public static String getTableName(String classPath) {
        String[] classPathSplit = classPath.split("\\.");
        String className = classPathSplit[classPathSplit.length - 1].toLowerCase();
        if (className.charAt(className.length() - 1) == 'y') {
            className = className.replace(className.charAt(className.length() - 1) + "", "ie");
        }
        className += "s";
        return className;
    }

    public static boolean checkUserCredentials(String email, String password) {
        return (new UserDAO()).getAll().stream().anyMatch(u -> u.getEmail().equals(email) && u.getHashedPassword().equals(password));
    }

    public static boolean checkCompanyCredentials(String email, String password) {
        return (new CompanyDAO()).getAll().stream().anyMatch(c -> c.getEmail().equals(email) && c.getHashedPassword().equals(password));
    }

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

    public static void setObjectFields(Class<?> cls, HttpServletRequest req, Object userObject) {
        try {
            String resField;
            for (Field field : cls.getDeclaredFields()) {
                if (field.getName().equals("id")) {
                    continue;
                }
                Method m = cls.getDeclaredMethod("set" + Service.capitalize(field.getName()), String.class);
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
                    Service.writeToFile(part.getInputStream(), path, name);
                    resField = name;
                } else {
                    resField = new BufferedReader(new InputStreamReader(part.getInputStream()))
                            .lines().collect(Collectors.joining("\n"));
                }
                m.invoke(userObject, resField);
            }
        } catch (ServletException | IOException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
