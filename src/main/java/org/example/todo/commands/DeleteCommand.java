package org.example.todo.commands;

import lombok.extern.slf4j.Slf4j;
import java.util.Scanner;

@Slf4j
public class DeleteCommand extends BaseCommand{
    public final String NAME = "delete";
    @Override
    public String getCommand() {
        return NAME;
    }

    @Override
    public void execution(Scanner scanner) {
        String deleteId = scanner.nextLine().trim();
        int id = taskStorageImpl.parseId(deleteId);
        if (id == 0){
            return;
        }
        taskStorageImpl.delete(id);
    }
}
