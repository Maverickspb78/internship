package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.example.todo.TaskStorageImpl;

import java.util.Scanner;

@Slf4j
public class AddCommand extends BaseCommand {

    private final String NAME = "add";

    protected AddCommand(TaskStorageImpl taskStorageImpl) {
        super(taskStorageImpl);
    }

    @Override
    public String getCommand() { return "add"; }

    @Override
    public void execution(Scanner scanner) {
        String task = scanner.nextLine().trim();
        log.debug("command: add {}", task);
        if (task.length() == 0){
            System.err.println("Введите корректное описание задачи");
            return;
        }
        taskStorageImpl.setCountId(taskStorageImpl.getCountId() + 1);
        taskStorageImpl.getTaskMap().put(taskStorageImpl.getCountId(), new Task(task));
    }
}
