package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;

import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

@Slf4j
public class SearchCommand extends BaseCommand{
    public final String NAME = "search";

    @Override
    public String getCommand() {
        return NAME;
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
        Stream<Map.Entry<Integer, Task>> mapStream = taskStorageImpl.getTaskMap().entrySet().stream();
        mapStream.filter(a -> a.getValue().getDescription().contains(search)).forEach(PrintCommand::printTask);
    }
}
