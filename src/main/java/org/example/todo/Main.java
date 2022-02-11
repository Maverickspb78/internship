package org.example.todo;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.commands.CommandStorageImpl;
import org.example.todo.commands.PrintCommand;
import java.util.Scanner;


@Slf4j
public class Main {

    public static void main(String[] args) {
        TaskStorage taskStorage = new TaskStorage();
        PrintCommand printCommand = new PrintCommand(taskStorage);
        CommandStorage commandStorage = new CommandStorageImpl(taskStorage, printCommand);
        final Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String command = scanner.next();
            try {
                commandStorage.getCommand(command).execution(scanner);
            } catch (NullPointerException e){
                log.error("Ошибка команды: {} {}", e.getMessage(), e);
                System.err.println("Введена не правильная команда");
            }
        }
    }
}
