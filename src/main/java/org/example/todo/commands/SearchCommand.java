package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.Task;
import org.example.todo.TaskStorage;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class SearchCommand extends BaseCommand{
    protected SearchCommand(TaskStorage taskStorage) {
        super(taskStorage);
    }

    @Override
    public String getCommand() {
        return "search";
    }

    @Override
    public void execution(Scanner scanner) {
        String search = scanner.nextLine().trim();
        log.debug("command: search {}",search);
        if (search.length() == 0){
            log.debug("нет символов для поиска");
            System.err.println("введите хотя бы один символ для поиска");
            return;
        }
        Stream<Map.Entry<Integer, Task>> mapStream = taskStorage.getTaskMap().entrySet().stream();
        mapStream.filter(a -> a.getValue().getDescription().contains(search)).forEach(PrintCommand::printTask);
    }
}
