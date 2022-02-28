package org.example.todo.storage;

import org.example.todo.entities.Task;
import java.util.List;

public interface TaskStorage {
    void add(Task task);
    Task edit(Task task);
    void toggle(Long id);
    void delete(Long id);
    List<Task> getList();
}
