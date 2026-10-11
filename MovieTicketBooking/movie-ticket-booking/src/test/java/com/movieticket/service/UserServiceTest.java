package com.movieticket.service;

import com.movieticket.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void registerValidUser() {

        UserService service = new UserService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        assertTrue(service.register(user));
    }

    @Test
    void rejectDuplicateEmail() {

        UserService service = new UserService();

        User user1 = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        User user2 = new User(
                2,
                "Rahul",
                "kanishka@gmail.com",
                "password456"
        );

        service.register(user1);

        assertFalse(service.register(user2));
    }

    @Test
    void rejectEmptyName() {

        UserService service = new UserService();

        User user = new User(
                1,
                "",
                "kanishka@gmail.com",
                "password123"
        );

        assertFalse(service.register(user));
    }

    @Test
    void loginWithCorrectCredentials() {

        UserService service = new UserService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        service.register(user);

        assertTrue(
                service.login("kanishka@gmail.com", "password123")
        );
    }

    @Test
    void loginWithWrongPassword() {

        UserService service = new UserService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        service.register(user);

        assertFalse(
                service.login("kanishka@gmail.com", "wrongpassword")
        );
    }
}