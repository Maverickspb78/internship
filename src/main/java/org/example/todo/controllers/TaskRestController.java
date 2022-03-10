package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.todo.entities.Task;
import org.example.todo.servises.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Task> findAll(@RequestParam(name = "all", required = false) boolean all,
                              @RequestParam(name = "searchString", required = false) String searchString) {
        return taskService.getList(all, searchString);

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Task add(@Valid @RequestBody Task task){
        taskService.add(task);
        return task;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Task updateTask(@Valid @RequestBody Task task){
        return taskService.edit(task);
    }

    @PatchMapping(path = "/toggle", produces = "application/json")
    public void toggle(@Valid @RequestParam("id") Long id){
        taskService.toggle(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Valid @PathVariable("id") Long id){
        taskService.delete(id);
    }

}
