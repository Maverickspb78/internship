package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import java.util.Scanner;

@Slf4j
public class EditCommand extends BaseCommand{
    public final String NAME = "edit";

    PrintCommand printCommand;
    protected EditCommand(PrintCommand printCommand) {

        this.printCommand = printCommand;
    }

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        String editId = scanner.next().trim();
        String editDescription = scanner.nextLine().trim();
        log.debug("command: edit {} {}",editId, editDescription);
        int id = taskStorageImpl.parseId(editId);
        if (id == 0){
            return;
        }
        if (editDescription.length() != 0) {
            taskStorageImpl.edit(id, editDescription);
        } else {
            log.debug("Пустое имя задачи");
            System.err.println("Введите корректное описание задачи");
        }
    }
}
