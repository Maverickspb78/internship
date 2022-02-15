package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.Command;
import java.util.Scanner;

@Slf4j
public class QuitCommand implements Command {
    public final String NAME = "quit";

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        log.debug("command: quit");
        System.exit(0);
    }
}
