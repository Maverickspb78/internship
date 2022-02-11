package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.TaskStorage;
import java.util.Scanner;

@Slf4j
public class ToggleCommand extends BaseCommand{

    private PrintCommand printCommand;
    protected ToggleCommand(TaskStorage taskStorage, PrintCommand printCommand) {
        super(taskStorage);
        this.printCommand=printCommand;
    }

    @Override
    public String getCommand() {
        return "toggle";
    }

    @Override
    public void execution(Scanner scanner) {

        String toggleId = scanner.nextLine().trim();
        log.debug("command: toggle {}", toggleId);
        int id;
        try {
            id = Integer.parseInt(toggleId);
        } catch (NumberFormatException exception){
            log.error("Не распарсить id: {}", toggleId);
            printCommand.printError();
            return;
        }
        try {
            taskStorage.getTaskMap().get(id).setDone(!taskStorage.getTaskMap().get(id).isDone());
        } catch (NullPointerException exception){
            log.error("Не существующая задача: {}", id);
            printCommand.printError();
        }
    }
}
