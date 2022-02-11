package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.TaskStorage;
import java.util.Scanner;

@Slf4j
public class DeleteCommand extends BaseCommand{
    PrintCommand printCommand;
    protected DeleteCommand(TaskStorage taskStorage, PrintCommand printCommand) {
        super(taskStorage);
        this.printCommand = printCommand;
    }

    @Override
    public String getCommand() {
        return "delete";
    }

    @Override
    public void execution(Scanner scanner) {
        String deleteId = scanner.nextLine().trim();
        int id;
        log.debug("command: delete: {}", deleteId);
        try {
            id = Integer.parseInt(deleteId);
        } catch (NumberFormatException exception){
            log.error("Не распарсить id: {}", deleteId, exception);
            printCommand.printError();
            return;
        }
        if (taskStorage.getTaskMap().containsKey(id)) {
            taskStorage.getTaskMap().remove(id);
        } else {
            log.error("Не существующая задача: {}", id);
            printCommand.printError();
        }
    }
}