package org.example.todo.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.commandInt.Command;
import org.example.todo.storage.TaskStorage;
import org.example.todo.Utils.ParserId;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteCommand implements Command {

    private final TaskStorage taskStorage;
    private final ParserId parserId;
    public final String NAME = "delete";
    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        String deleteId = scanner.nextLine().trim();
        log.debug("command: delete {}", deleteId);
        int id = parserId.parseId(deleteId);
        if (id == 0){
            return;
        }
        taskStorage.delete(id);
    }
}
