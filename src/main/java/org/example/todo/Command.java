package org.example.todo;

import java.util.Scanner;

public interface Command {

    void execution(Scanner scanner);
    String getCommand();

}
