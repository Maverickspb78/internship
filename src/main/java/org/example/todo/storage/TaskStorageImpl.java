package org.example.todo.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.Utils.PrintError;
import org.example.todo.entities.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskStorageImpl implements TaskStorage {

    private final PrintError printError;
    private int countId = 0;
    private final Map<Integer, Task> taskMap = new HashMap<>();

    @Override
    public void add(String task){
        countId++;
        taskMap.put(countId, new Task(task));
    }

    @Override
    public void delete(int id){
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
        } else {
            log.error("Не существующая задача: {}", id);
            printError.printError();
        }
    }

    @Override
    public void edit(int id, String Description){
        if (taskMap.containsKey(id)) {
            Task task = taskMap.get(id);
            task.setDescription(Description);
            taskMap.replace(id, task);
        } else {
            log.error("Не существующая задача: {}", id);
            printError.printError();
        }
    }

    @Override
    public void toggle(int id) {
        try {
            taskMap.get(id).setDone(!taskMap.get(id).isDone());
        } catch (NullPointerException exception){
            log.error("Не существующая задача: {}", id);
            printError.printError();
        }

    }

    @Override
    public Stream<Map.Entry<Integer, Task>> getStream() {
        return taskMap.entrySet().stream();
    }
}
