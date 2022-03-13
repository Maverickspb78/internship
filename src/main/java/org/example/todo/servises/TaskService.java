package org.example.todo.servises;

import org.example.todo.entities.Task;
import java.security.Principal;
import java.util.List;

public interface TaskService {

    void add(Task task, Principal principal);
    Task edit(Task task, Principal principal);
    void toggle(Long id, Principal principal);
    void delete(Long id, Principal principal);
    List<Task> getList(boolean all, String searchString, Principal principal);
}
