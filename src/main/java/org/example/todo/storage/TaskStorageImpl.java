package org.example.todo.storage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskStorageImpl implements TaskStorage {

    private long countId = 0;
    private final List<Task> taskList = new ArrayList<>();

    @Override
    public void add(Task task){
        countId++;
        task.setId(countId);
        log.debug("add {} ", task);
        taskList.add(task);
    }

    @Override
    public void delete(Long id) {
        taskList.remove(findById(id));
        log.debug("taskList.remove({})", id);
    }

    @Override
    public Task edit(Task task){
        Task taskEdit = findById(task.getId());
        log.debug("editTask {}, new task {}", taskEdit, task);
        taskEdit.setDescription(task.getDescription());
        return taskEdit;
    }

    @Override
    public void toggle(Long id) {
        try {
            findById(id).setDone(!findById(id).isDone());
        }catch (NullPointerException e){
            System.err.println("wrong id: " + id);
        }
        log.debug("toggle id={}", id);
    }

    public Task findById(Long id){
        Task taskR;
        for (Task task : taskList){
            if (Objects.equals(task.getId(), id)){
                taskR = task;
                log.debug("findById({})", id);
                return taskR;
            }
        }
        return null;
    }

    @Override
    public List<Task> search(String searchString) {
        return taskList.stream().filter(a->a.getDescription().contains(searchString)).collect(Collectors.toList());
    }

    @Override
    public List<Task> getList(boolean all) {
        if (!all){
            return taskList.stream().filter(a->!a.isDone()).collect(Collectors.toList());
        }
        return taskList;
    }
}
