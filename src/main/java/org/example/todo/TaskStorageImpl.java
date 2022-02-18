package org.example.todo;

import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TaskStorageImpl implements TaskStorage{

    private int countId = 0;
    private final Map<Integer, Task> taskMap = new HashMap<>();

    public int parseId(String idString){
        int id;
        log.debug("parseId: {}", idString);
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException exception){
            log.error("Не распарсить id: {}", idString, exception);
            printError();
            return 0;
        } return id;
    }

    public void printError(){
        System.err.println("Не верный id");
    }

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
            printError();
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
            printError();
        }
    }

    @Override
    public void toggle(int id) {
        try {
            taskMap.get(id).setDone(!taskMap.get(id).isDone());
        } catch (NullPointerException exception){
            log.error("Не существующая задача: {}", id);
            printError();
        }

    }

    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }
}
