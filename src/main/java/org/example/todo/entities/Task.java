package org.example.todo.entities;

import lombok.Data;

@Data
public class Task {

    private boolean done;
    private String description;

    public Task(String description) {
        this.description = description;
    }
}
