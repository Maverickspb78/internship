package org.example.todo.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.commandInt.Command;
import org.example.todo.storage.TaskStorage;
import org.example.todo.entities.Task;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class PrintCommand implements Command {

    private final TaskStorage taskStorage;
    public final String NAME = "print";

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        String line = scanner.nextLine().trim();
        boolean all = line.equals("all");
        log.debug("command: print {}", line);
        if (!all && line.length() > 0){
            log.debug("введен ен верный аргумент: {}", line);
            System.err.println("Введен не верный аргумент");
            return;
        }
        if (!all){
            taskStorage.getStream().filter(a->!a.getValue().isDone()).forEach(PrintCommand::printTask);
        } else {
            taskStorage.getStream().forEach(PrintCommand::printTask);
        }
    }

    static void printTask(Map.Entry<Integer, Task> entry){
        System.out.printf("%d. [%s] %s\n", entry.getKey(),
                entry.getValue().isDone() ? "X" : " ",
                entry.getValue().getDescription());

        log.debug("Вывод: {} [{}] {}", entry.getKey(),
                entry.getValue().isDone() ? "X" : " ",
                entry.getValue().getDescription());
    }
}
