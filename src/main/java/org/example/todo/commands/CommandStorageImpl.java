package org.example.todo.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.commandInt.Command;
import org.example.todo.commandInt.CommandStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandStorageImpl implements CommandStorage {


    private final Map<String, Command> commandMap;

    private final Command addCommand;
    private final Command printCommand;
    private final Command searchCommand;
    private final Command toggleCommand;
    private final Command deleteCommand;
    private final Command editCommand;
    private final Command quitCommand;

    @Autowired
    public CommandStorageImpl(Command addCommand, Command printCommand, Command searchCommand,
                              Command toggleCommand, Command deleteCommand, Command editCommand, Command quitCommand) {
        this.addCommand = addCommand;
        this.printCommand = printCommand;
        this.searchCommand = searchCommand;
        this.toggleCommand = toggleCommand;
        this.deleteCommand = deleteCommand;
        this.editCommand = editCommand;
        this.quitCommand = quitCommand;
        Map<String, Command> map = new HashMap<>();
        Command command = this.addCommand;
        map.put(command.getCommand(), command);

        command = printCommand;
        map.put(command.getCommand(), command);

        command = searchCommand;
        map.put(command.getCommand(), command);

        command = toggleCommand;
        map.put(command.getCommand(), command);

        command = deleteCommand;
        map.put(command.getCommand(), command);

        command = editCommand;
        map.put(command.getCommand(), command);

        command = quitCommand;
        map.put(command.getCommand(), command);

        this.commandMap = Collections.unmodifiableMap(map);
    }

    @Override
    public Command getCommand(String command) {
        log.debug("CS: {}", commandMap. get("add"));
        return commandMap.get(command);
    }
}
