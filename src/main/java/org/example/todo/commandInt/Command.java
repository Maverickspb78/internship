package org.example.todo.commandInt;

import java.util.Scanner;

public interface Command {

    void execution(Scanner scanner);
    String getCommand();

}
