package com.example.semester.utils;

import com.example.semester.DAO.SubDAO;
import com.example.semester.DAO.UserDAO;
import com.example.semester.DAO.VacancyDAO;
import com.example.semester.models.Sub;
import com.example.semester.models.User;
import com.example.semester.models.Vacancy;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationService {
    public static List<Vacancy> getVacancyRecommendations(User currentUser, int offset) {
        List<User> currentUsersAuthors;
        // percent of users' subscriptions similarity
        int threshold = 0;
        currentUsersAuthors = StorageService.getCurrentUserAuthors(currentUser.getEmail(), offset);
        for (User otherUser : currentUsersAuthors) {
            if (getSubsSimilarity(currentUser, otherUser) > threshold) {
                return getOtherUserVacancies(currentUser, otherUser);
            }
        }
        return new LinkedList<>();
    }

    public static int getSubsSimilarity(User currentUser, User otherUser) {
        SubDAO subDAO = new SubDAO();
        List<Integer> currentUserSubIds = subDAO
                .getAll()
                .stream()
                .filter(s -> s.getUserId() == currentUser.getId())
                .map(Sub::getVacancyId)
                .collect(Collectors.toList());
        int currentUserSubIdsSize = currentUserSubIds.size();
        List<Integer> otherUserSubIds = subDAO
                .getAll()
                .stream()
                .filter(s -> s.getUserId() == otherUser.getId())
                .map(Sub::getVacancyId)
                .collect(Collectors.toList());

        currentUserSubIds.retainAll(otherUserSubIds);

        return 100 * currentUserSubIds.size() / currentUserSubIdsSize;
    }

    public static List<Vacancy> getOtherUserVacancies(User currentUser, User otherUser) {
        VacancyDAO vacancyDAO = new VacancyDAO();
        SubDAO subDAO = new SubDAO();

        List<Integer> currentUserSubIds = subDAO
                .getAll()
                .stream()
                .filter(s -> s.getUserId() == currentUser.getId())
                .map(Sub::getVacancyId)
                .collect(Collectors.toList());
        List<Integer> otherUserSubIds = subDAO
                .getAll()
                .stream()
                .filter(s -> s.getUserId() == otherUser.getId())
                .map(Sub::getVacancyId)
                .collect(Collectors.toList());

        otherUserSubIds.removeAll(currentUserSubIds);
        return vacancyDAO
                .getAll()
                .stream()
                .filter(v -> otherUserSubIds.contains(v.getId()))
                .collect(Collectors.toList());
    }

    public static List<User> getUserRecommendations(User currentUser, int offset) {
        UserDAO userDAO = new UserDAO();
        int threshold = 0;
        List<User> recommendedUsers = new LinkedList<>();
        for (User otherUser : userDAO.getAll()) {
            if (otherUser.getId() == currentUser.getId()) {
                continue;
            }
            List<Integer> currentUserAuthorsIds = StorageService
                    .getCurrentUserAuthors(currentUser.getEmail(), offset)
                    .stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            int currentUserAuthorsIdsSize = currentUserAuthorsIds.size();
            List<Integer> otherUserAuthorsIds = StorageService
                    .getCurrentUserAuthors(otherUser.getEmail(), offset)
                    .stream()
                    .map(User::getId)
                    .collect(Collectors.toList());
            currentUserAuthorsIds.retainAll(otherUserAuthorsIds);
            int similarity = (100 * currentUserAuthorsIds.size()) / currentUserAuthorsIdsSize;
            System.out.println(otherUser.getName() + " " + currentUserAuthorsIds + "; " + otherUserAuthorsIds + "; " + similarity);
            if (similarity > threshold) {
                recommendedUsers.add(otherUser);
            }
        }
        System.out.println("Recommended users: " + recommendedUsers);
        return recommendedUsers;
    }
}
