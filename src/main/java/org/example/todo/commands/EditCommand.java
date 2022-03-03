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
public class EditCommand implements Command {

    private final TaskStorage taskStorage;
    private final ParserId parserId;
    public final String NAME = "edit";

    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        String editId = scanner.next().trim();
        String editDescription = scanner.nextLine().trim();
        log.debug("command: edit {} {}",editId, editDescription);
        int id = parserId.parseId(editId);
        if (id == 0){
            return;
        }
        if (editDescription.length() != 0) {
            taskStorage.edit(id, editDescription);
        } else {
            log.debug("Пустое имя задачи");
            System.err.println("Введите корректное описание задачи");
        }
    }
}
