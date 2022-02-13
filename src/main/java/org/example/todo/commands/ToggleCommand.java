package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.TaskStorageImpl;
import java.util.Scanner;

@Slf4j
public class ToggleCommand extends BaseCommand{

    private PrintCommand printCommand;
    protected ToggleCommand(TaskStorageImpl taskStorageImpl, PrintCommand printCommand) {
        super(taskStorageImpl);
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
            taskStorageImpl.getTaskMap().get(id).setDone(!taskStorageImpl.getTaskMap().get(id).isDone());
        } catch (NullPointerException exception){
            log.error("Не существующая задача: {}", id);
            printCommand.printError();
        }
    }
}
