package org.example.todo;

import lombok.Getter;
import lombok.Setter;
import org.example.todo.entities.Task;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class TaskStorageImpl implements TaskStorage{

    private TaskStorageImpl taskStorageImpl;
    private int countId = 0;
    private Map<Integer, Task> taskMap = new HashMap<>();

    public TaskStorageImpl getInstance(){
        if (taskStorageImpl == null){
            taskStorageImpl = new TaskStorageImpl();

        }
        return taskStorageImpl;
    }


}
