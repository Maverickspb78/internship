package org.example.todo;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.commands.CommandStorageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Scanner;

@Slf4j
@SpringBootApplication
public class ToDoApplication implements CommandLineRunner {

	@Autowired
	CommandStorageImpl commandStorage;

	public static void main(String[] args) {
		SpringApplication.run(ToDoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		final Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			String command = scanner.next();
			try {
				log.debug("get.command: {}",commandStorage.getCommand(command).getCommand());
				commandStorage.getCommand(command).execution(scanner);
			} catch (NullPointerException e){
				log.error("Ошибка команды: {} {}", e.getMessage(), e);
				System.err.println("Введена не правильная команда");
			}
		}
	}
}
