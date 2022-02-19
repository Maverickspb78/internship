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
public class ToggleCommand implements Command {

    private final TaskStorage taskStorage;
    private final ParserId parserId;
    public final String NAME = "toggle";

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {

        String toggleId = scanner.nextLine().trim();
        log.debug("command: toggle {}", toggleId);
        int id = parserId.parseId(toggleId);
        if (id == 0){
            return;
        }
        taskStorage.toggle(id);
    }
}
