package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.todo.entities.User;
import org.example.todo.servises.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('user:all')")
    public User add(@Valid @RequestBody User user, Principal principal){
        log.debug("Начало add. principal {}", principal.getName());
        userService.addUser(user);
        user.setPassword("***");
        return user;
    }
}