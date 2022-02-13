package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.CommandStorage;
import org.example.todo.TaskStorageImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandStorageImpl implements CommandStorage {

    private final Map<String, Command> commandMap;
    private final TaskStorageImpl taskStorageImpl = new TaskStorageImpl().getInstance();
    private final PrintCommand printCommand = new PrintCommand(taskStorageImpl);

    public CommandStorageImpl() {
        Map<String, Command> map = new HashMap<>();
        Command command = new AddCommand(taskStorageImpl);
        map.put(command.getCommand(), command);

        command = new PrintCommand(taskStorageImpl);
        map.put(command.getCommand(), command);

        command = new SearchCommand(taskStorageImpl);
        map.put(command.getCommand(), command);

        command = new ToggleCommand(taskStorageImpl, printCommand);
        map.put(command.getCommand(), command);

        command = new DeleteCommand(taskStorageImpl, printCommand);
        map.put(command.getCommand(), command);

        command = new EditCommand(taskStorageImpl, printCommand);
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
