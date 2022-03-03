package org.example.todo.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.commandInt.Command;
import org.example.todo.storage.TaskStorage;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddCommand implements Command {

    private final TaskStorage taskStorage;
    public final String NAME = "add";

    @Override
    public String getCommand() { return NAME; }

    @Override
    public void execution(Scanner scanner) {

        String task = scanner.nextLine().trim();
        log.debug("User command: add {}", task);
        if (task.length() == 0){
            System.err.println("Введите корректное описание задачи");
            return;
        }
        log.debug("TS before taskStorage.add(task) - {}", taskStorage);
        taskStorage.add(task);
    }
}
