package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.Task;
import org.example.todo.TaskStorage;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class PrintCommand extends BaseCommand{
    public PrintCommand(TaskStorage taskStorage) {
        super(taskStorage);
    }

    @Override
    public String getCommand() {
        return "print";
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
        Stream<Map.Entry<Integer, Task>> mapStream = taskStorage.getTaskMap().entrySet().stream();
        if (!all){
            mapStream.filter(a->!a.getValue().isDone()).forEach(PrintCommand::printTask);
        } else {
            mapStream.forEach(PrintCommand::printTask);
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
    static void printError(){
            System.err.println("Не верный id");
    }
}
