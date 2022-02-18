package org.example.todo.commands;

import org.example.todo.Command;
import org.example.todo.TaskStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public abstract class BaseCommand implements Command {

    protected BaseCommand(){}

}