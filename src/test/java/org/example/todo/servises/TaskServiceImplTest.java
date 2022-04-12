package org.example.todo.servises;

import org.example.todo.entities.Role;
import org.example.todo.entities.Task;
import org.example.todo.entities.User;
import org.example.todo.repositories.TaskRepository;
import org.example.todo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TaskServiceImplTest {

    private TaskService taskService;
    private User userGeneral;
    private Task taskGeneral;
    private Principal principal;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void create() {

        taskRepository = mock(TaskRepository.class);
        userRepository = mock(UserRepository.class);
        taskService = new TaskServiceImpl(taskRepository, userRepository);
        principal = mock(Principal.class);
        userGeneral = new User(1L, "user", "user", Role.USER);
        taskGeneral = new Task();

    }


    @Test
    public void add_Task_Always() {
        Task taskAdd = new Task();
        User owner = new User();

        when(userRepository.findUserByUsername(any())).thenReturn(owner);

        taskService.add(taskAdd, principal);

        assertEquals(owner, taskAdd.getUser());

        verify(taskRepository).save(taskAdd);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void delete() {
    }

    @Test
    public void edit_FindTask_AndSetDescription_AndSave() {
        Task task = new Task();
        Task taskEdit = new Task();
        taskEdit.setId(1L);
        taskEdit.setDescription("Task10");
        task.setDescription("Task1");

        when(taskRepository.findFirstByIdAndUserEquals(task.getId(),
                userRepository.findUserByUsername(any())))
                .thenReturn(Optional.of(taskEdit));

        taskService.edit(task, principal);

        assertEquals(task.getDescription(), taskEdit.getDescription());

        verify(taskRepository).save(taskEdit);
    }

    @Test
    public void toggle_FindById_And_SetDone_AndSave() {
        Long id = 1L;
        Task task = new Task();
        when(taskRepository.findFirstByIdAndUserEquals(id,
                userRepository.findUserByUsername(any())))
                .thenReturn(Optional.of((task)));

        taskService.toggle(id, principal);

        verify(taskRepository).findFirstByIdAndUserEquals(id, userRepository.findUserByUsername(any()));

        assertTrue(task.isDone());

        verify(taskRepository).save(task);
        verifyNoMoreInteractions(taskRepository);
    }


    @Test
    public void getList() {
    }

    public Task findById(Long id, Principal principal) {
        return taskRepository.findFirstByIdAndUserEquals(id,
                userRepository.findUserByUsername(principal.getName())).orElseThrow();
    }

    public Task findById(Task task, Principal principal){
        return taskRepository.findFirstByIdAndUserEquals(task.getId(),
                userRepository.findUserByUsername(principal.getName())).orElseThrow();
    }
}