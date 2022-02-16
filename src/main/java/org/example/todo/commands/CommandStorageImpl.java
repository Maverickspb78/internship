package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.CommandStorage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandStorageImpl implements CommandStorage {

    private final Map<String, Command> commandMap;
    private final PrintCommand printCommand = new PrintCommand();

    public CommandStorageImpl() {
        Map<String, Command> map = new HashMap<>();
        Command command = new AddCommand();
        map.put(command.getCommand(), command);

        command = new PrintCommand();
        map.put(command.getCommand(), command);

        command = new SearchCommand();
        map.put(command.getCommand(), command);

        command = new ToggleCommand();
        map.put(command.getCommand(), command);

        command = new DeleteCommand();
        map.put(command.getCommand(), command);

        command = new EditCommand(printCommand);
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
