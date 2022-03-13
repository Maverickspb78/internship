package org.example.todo.servises;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todo.entities.Task;
import org.example.todo.repositories.TaskRepository;
import org.example.todo.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public void add(Task task, Principal principal){
        task.setUser(userRepository.findUserByUsername(principal.getName()));
        taskRepository.save(task);
        log.debug("add {} ", task);
    }

    @Override
    @Transactional
    public void delete(Long id, Principal principal) {
        taskRepository.deleteByIdAndUserEquals(id, userRepository.findUserByUsername(principal.getName()));
        log.debug("taskList.remove({})", id);
    }

    @Override
    public Task edit(Task task, Principal principal){
        Task taskEdit = findById(task, principal);
        log.debug("editTask {}, new task {}", taskEdit, task);
        taskEdit.setDescription(task.getDescription());
        taskRepository.save(task);
        return taskEdit;
    }

    @Override
    public void toggle(Long id, Principal principal) {
        Task task = findById(id, principal);
        task.setDone(!taskRepository.findById(id).orElseThrow().isDone());
        taskRepository.save(task);
        log.debug("toggle id={}", id);
    }

    public Task findById(Long id, Principal principal){
        return taskRepository.findFirstByIdAndUserEquals(id,
                userRepository.findUserByUsername(principal.getName())).orElseThrow();
    }

    public Task findById(Task task, Principal principal){
        return taskRepository.findFirstByIdAndUserEquals(task.getId(),
                userRepository.findUserByUsername(principal.getName())).orElseThrow();
    }

    @Override
    public List<Task> getList(boolean all, String searchString, Principal principal) {
        if (!all) {
            return taskRepository.findAllByUserEqualsAndDoneFalseAndDescriptionContains(userRepository.findUserByUsername(principal.getName()),
                    searchString);
        } else {
            return taskRepository.findAllByUserEqualsAndDescriptionContains(userRepository.findUserByUsername(principal.getName()),searchString);
        }
    }
}
