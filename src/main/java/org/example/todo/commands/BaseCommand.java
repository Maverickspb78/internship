package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.TaskStorageImpl;


public abstract class BaseCommand implements Command {

    protected final TaskStorageImpl taskStorageImpl;

    protected BaseCommand(TaskStorageImpl taskStorageImpl) {
        this.taskStorageImpl = taskStorageImpl;
    }
}
