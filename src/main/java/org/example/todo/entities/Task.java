package org.example.todo.entities;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Task {

    @NotBlank
    @NotNull
    private Long id;
    private boolean done;
    @NotBlank
    @NotNull
    private String description;

    public Task(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}
