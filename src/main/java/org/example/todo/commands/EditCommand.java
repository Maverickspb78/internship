package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.example.todo.TaskStorageImpl;
import java.util.Scanner;

@Slf4j
public class EditCommand extends BaseCommand{
    PrintCommand printCommand;
    protected EditCommand(TaskStorageImpl taskStorageImpl, PrintCommand printCommand) {
        super(taskStorageImpl);
        this.printCommand = printCommand;
    }

    @Override
    public String getCommand() {
        return "edit";
    }

    @Override
    public void execution(Scanner scanner) {
        String editId = scanner.next().trim();
        String editDescription = scanner.nextLine().trim();
        log.debug("command: edit {} {}",editId, editDescription);
        int id;
        try {
            id = Integer.parseInt(editId);
        } catch (NumberFormatException exception) {
            log.error("Не распарсить id: {}", editId);
            printCommand.printError();
            return;
        }
        if (editDescription.length() != 0) {
            if (taskStorageImpl.getTaskMap().containsKey(id)) {
                Task task = taskStorageImpl.getTaskMap().get(id);
                task.setDescription(editDescription);
                taskStorageImpl.getTaskMap().replace(id, task);
            } else {
                log.error("Не существующая задача: {}", id);
                printCommand.printError();
            }
        } else {
            log.debug("Пустое имя задачи");
            System.err.println("Введите корректное описание задачи");
        }
    }
}
