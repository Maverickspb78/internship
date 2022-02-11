package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.TaskStorage;


public abstract class BaseCommand implements Command {

    protected final TaskStorage taskStorage;

    protected BaseCommand(TaskStorage taskStorage) {
        this.taskStorage = taskStorage;
    }
}
