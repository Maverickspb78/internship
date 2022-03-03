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
public class SearchCommand implements Command {

    private final TaskStorage taskStorage;
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

        taskStorage.getStream().filter(a -> a.getValue().getDescription().contains(search)).forEach(PrintCommand::printTask);
    }
}
