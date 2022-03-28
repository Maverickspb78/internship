package org.example.todo.servises;

import org.example.todo.entities.User;

public interface UserService {

    User getUserByUsername(String name);
    User addUser(User user);
}