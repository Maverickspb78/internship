package org.example.todo.servises;

import org.example.todo.entities.Role;
import org.example.todo.entities.User;
import org.example.todo.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private User user;
    private  UserService userService;

    @BeforeEach
    public void create(){
        userRepository = mock(UserRepository.class);
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder);
        user = new User();

    }

    @Test
    public void addUser() {
        String password = "pass";
        user.setPassword(password);
        user.setUsername("userName");
        user.setRole(Role.USER);

        User newUser = userService.addUser(user);

        assertNotEquals(password, newUser.getPassword());

        assertEquals(newUser.getRole(), Role.USER);

        verify(userRepository).save(user);
        verifyNoMoreInteractions(userRepository);


    }
}