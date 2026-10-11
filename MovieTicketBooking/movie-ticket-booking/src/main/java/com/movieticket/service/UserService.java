package com.movieticket.service;

import com.movieticket.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> users = new ArrayList<>();

    public boolean register(User user) {

        if (user == null) {
            return false;
        }

        if (user.getName() == null || user.getName().isBlank()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().isBlank()) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return false;
        }

        for (User existingUser : users) {
            if (existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                return false;
            }
        }

        users.add(user);
        return true;
    }

    public boolean login(String email, String password) {

        for (User user : users) {

            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password)) {

                return true;
            }
        }

        return false;
    }
}
