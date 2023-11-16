package com.example.semester.utils;

import com.example.semester.DAO.CompanyDAO;
import com.example.semester.DAO.UserDAO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordProcessor {
    public static String getHashedPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] data = password.getBytes(StandardCharsets.UTF_8);
            byte[] processed = md.digest(data);
            return new String(processed, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkUserCredentials(String email, String password) {
        return (new UserDAO()).getAll().stream().anyMatch(u -> u.getEmail().equals(email) && u.getHashedPassword().equals(password));
    }

    public static boolean checkCompanyCredentials(String email, String password) {
        return (new CompanyDAO()).getAll().stream().anyMatch(c -> c.getEmail().equals(email) && c.getHashedPassword().equals(password));
    }
}
