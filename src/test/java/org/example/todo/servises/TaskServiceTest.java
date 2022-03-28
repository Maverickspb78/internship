package org.example.todo.servises;

import org.example.todo.entities.Role;
import org.example.todo.entities.Task;
import org.example.todo.entities.User;
import org.example.todo.repositories.TaskRepository;
import org.junit.jupiter.api.Test;


import java.security.Principal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    TaskService taskService = mock(TaskServiceImpl.class);
    TaskRepository taskRepository = mock(TaskRepository.class);
    Principal principal = mock(Principal.class);
    User user = new User(1L, "admin", "admin", Role.ADMIN);
    Task task = new Task(1L, false, "Task #1", user);

    @Test
    public void add_Always(){
        taskService.add(task, principal);
        when(taskRepository.findById(anyLong())).thenReturn(Optional.ofNullable(task));
        assertEquals(taskRepository.findById(1L), Optional.ofNullable(task));
    }

    @Test
    public void edit_Always() {
        String description = "Task #3";
        when(taskService.edit(any(),any())).thenReturn(new Task(1L, false, description, user));
        assertNotEquals(taskService.edit(task, principal).getDescription(), task.getDescription());
    }
}