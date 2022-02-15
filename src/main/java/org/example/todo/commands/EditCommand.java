package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.Task;
import org.example.todo.TaskStorage;
import java.util.Scanner;

@Slf4j
public class EditCommand extends BaseCommand{
    PrintCommand printCommand;
    protected EditCommand(TaskStorage taskStorage, PrintCommand printCommand) {
        super(taskStorage);
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
            if (taskStorage.getTaskMap().containsKey(id)) {
                Task task = taskStorage.getTaskMap().get(id);
                task.setDescription(editDescription);
                taskStorage.getTaskMap().replace(id, task);
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
