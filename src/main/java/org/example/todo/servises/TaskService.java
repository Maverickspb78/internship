package org.example.todo.servises;

import org.example.todo.entities.Task;
import java.util.List;

public interface TaskService {
    void add(Task task);
    Task edit(Task task);
    void toggle(Long id);
    void delete(Long id);
    List<Task> getList(boolean all, String searchString);
}
