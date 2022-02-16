package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.TaskStorageImpl;

public abstract class BaseCommand implements Command {

    protected TaskStorageImpl taskStorageImpl;

    protected BaseCommand() {
        taskStorageImpl = TaskStorageImpl.getInstance();
    }
}