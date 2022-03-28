package org.example.todo.controllers;

import org.example.todo.entities.Role;
import org.example.todo.entities.Task;
import org.example.todo.entities.User;
import org.example.todo.servises.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.security.Principal;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TaskRestControllerTest {

    private TaskRestController taskRestController;
    private TaskService taskService;
    private Principal principal;
    private User user;
    private Task task;

    @BeforeEach
    public void create() {
        taskService = mock(TaskService.class);
        taskRestController = new TaskRestController(taskService);
        principal = mock(Principal.class);
        user = new User(1L, "user", "user", Role.USER);
        task = new Task();
        task.setUser(user);
        task.setDescription("Task #10");

    }

    @Test
    public void findAll_NotEmpty() {
        when(taskService.getList(anyBoolean(), anyString(), any())).thenReturn(Arrays.asList(
                new Task(1L, false, "Task #1", user),
                new Task(2L, false, "Task #2", user)));

        assertFalse(taskRestController.findAll(true, "", principal).isEmpty());
    }

    @Test
    public void add_Always(){
        Task taskRes = taskRestController.add(task, principal);

        assertEquals(taskRes, task);

        verify(taskService).add(task, principal);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    public void updateTask_Always(){

        Task taskRes = taskRestController.updateTask(task, principal);


    }
}