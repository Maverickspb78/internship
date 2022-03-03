package org.example.todo.storage;

import org.example.todo.entities.Task;

import java.util.Map;
import java.util.stream.Stream;

public interface TaskStorage {

    void add(String task);
    void edit(int id, String Description);
    void toggle(int id);
    void delete(int id);
    Stream<Map.Entry<Integer, Task>> getStream();
}
