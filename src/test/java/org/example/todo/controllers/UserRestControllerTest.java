package org.example.todo.controllers;

import org.example.todo.entities.User;
import org.example.todo.servises.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class UserRestControllerTest {
    private final static String PASS = "pass";
    private User user;
    private UserRestController userRestController;
    private UserService userService;
    private Principal principal;

    @BeforeEach
    void create (){
        principal = mock(Principal.class);
        userService = mock(UserService.class);
        user = creatUser();
        userRestController = new UserRestController(userService);
        when(userService.addUser(eq(user))).thenReturn(creatUser());
    }

    @Test
    void add_ErasePassword_Always(){
        User newUser = userRestController.add(user, principal);
        assertNotEquals(creatUser().getPassword(), newUser.getPassword());
    }

    @Test
    void add_AddUser_Always() {
        userRestController.add(user, principal);
        verify(userService).addUser(user);
        verifyNoMoreInteractions(userService);
    }

    private static User creatUser(){
        User testUser = new User();
        testUser.setUsername("User");
        testUser.setPassword(PASS);
        return testUser;
    }
}