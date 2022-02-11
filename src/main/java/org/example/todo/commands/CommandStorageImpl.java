package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.CommandStorage;
import org.example.todo.TaskStorage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandStorageImpl implements CommandStorage {

    private final Map<String, Command> commandMap;

    public CommandStorageImpl(TaskStorage taskStorage, PrintCommand printCommand) {
        Map<String, Command> map = new HashMap<>();
        Command command = new AddCommand(taskStorage);
        map.put(command.getCommand(), command);

        command = new PrintCommand(taskStorage);
        map.put(command.getCommand(), command);

        command = new SearchCommand(taskStorage);
        map.put(command.getCommand(), command);

        command = new ToggleCommand(taskStorage, printCommand);
        map.put(command.getCommand(), command);

        command = new DeleteCommand(taskStorage, printCommand);
        map.put(command.getCommand(), command);

        command = new EditCommand(taskStorage, printCommand);
        map.put(command.getCommand(), command);

        command = new QuitCommand();
        map.put(command.getCommand(), command);

        this.commandMap = Collections.unmodifiableMap(map);
    }

    @Override
    public Command getCommand(String command) {
        return commandMap.get(command);
    }
}
