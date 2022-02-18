package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.TaskStorageImpl;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
public class ToggleCommand extends BaseCommand{
    private TaskStorageImpl taskStorageImpl;
    public final String NAME = "toggle";

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {

        String toggleId = scanner.nextLine().trim();
        log.debug("command: toggle {}", toggleId);
        int id = taskStorageImpl.parseId(toggleId);
        if (id == 0){
            return;
        }
        taskStorageImpl.toggle(id);
    }
}
