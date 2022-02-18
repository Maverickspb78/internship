package org.example.todo;

public interface TaskStorage {

    void add(String task);
    void edit(int id, String Description);
    void toggle(int id);
    void delete(int id);
}
