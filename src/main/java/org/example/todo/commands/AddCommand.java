package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.Task;
import org.example.todo.TaskStorage;

import java.util.Scanner;

@Slf4j
public class AddCommand extends BaseCommand {

    private final String NAME = "add";

    protected AddCommand(TaskStorage taskStorage) {
        super(taskStorage);
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
        taskStorage.setCountId(taskStorage.getCountId() + 1);
        taskStorage.getTaskMap().put(taskStorage.getCountId(), new Task(task));
    }
}
