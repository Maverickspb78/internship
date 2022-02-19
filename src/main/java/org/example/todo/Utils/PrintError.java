package org.example.todo.Utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PrintError {

    public void printError(){
        System.err.println("Не верный id");
    }
}
