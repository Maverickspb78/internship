package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import java.util.Scanner;

@Slf4j
public class AddCommand extends BaseCommand {

    public final String NAME = "add";

    @Override
    public String getCommand() { return NAME; }

    @Override
    public void execution(Scanner scanner) {
        String task = scanner.nextLine().trim();
        log.debug("command: add {}", task);
        if (task.length() == 0){
            System.err.println("Введите корректное описание задачи");
            return;
        }
        taskStorageImpl.add(task);
    }
}
