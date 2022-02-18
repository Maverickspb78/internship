package org.example.todo.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.Command;
import org.example.todo.CommandStorage;
import org.example.todo.TaskStorage;
import org.example.todo.TaskStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.debug("CS: {}", commandMap. get("add"));
        return commandMap.get(command);
    }
}
