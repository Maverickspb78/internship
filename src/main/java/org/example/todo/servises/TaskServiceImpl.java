package org.example.todo.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.example.todo.repositories.TaskRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public void add(Task task){
        taskRepository.save(task);
        log.debug("add {} ", task);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
        log.debug("taskList.remove({})", id);
    }

    @Override
    public Task edit(Task task){
        Task taskEdit = taskRepository.findById(task.getId()).orElseThrow();
        log.debug("editTask {}, new task {}", taskEdit, task);
        taskEdit.setDescription(task.getDescription());
        taskRepository.save(task);
        return taskEdit;
    }

    @Override
    public void toggle(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setDone(!taskRepository.findById(id).orElseThrow().isDone());
        taskRepository.save(task);
        log.debug("toggle id={}", id);
    }

    public Task findById(Long id){
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Task> getList(boolean all, String searchString) {
        if (searchString != null) {
            if (!all) {
                return taskRepository.findAll().stream().filter(a -> !a.isDone()).filter(a -> a.getDescription().contains(searchString)).collect(Collectors.toList());
            } else {
                return taskRepository.findAll().stream().filter(a->a.getDescription().contains(searchString)).collect(Collectors.toList());
            }
        }
        if (!all) {
            return taskRepository.findAll().stream().filter(a -> !a.isDone()).collect(Collectors.toList());
        }
        return taskRepository.findAll();
    }
}
