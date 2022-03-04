package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.todo.entities.Task;
import org.example.todo.storage.TaskStorage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskRestController {

    private final TaskStorage taskStorage;

    @GetMapping(path = "/list", produces = "application/json")
    public List<Task> findAll(@RequestParam(name = "all", required = false) boolean all,
                              @RequestParam(name = "searchString", required = false) String searchString) {
        return taskStorage.getList(all, searchString);

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Task add(@Valid @RequestBody Task task){
        taskStorage.add(task);
        return task;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Task updateTask(@Valid @RequestBody Task task){
        return taskStorage.edit(task);
    }

    @PatchMapping(path = "/toggle", produces = "application/json")
    public void toggle(@Valid @RequestParam("id") Long id){
        taskStorage.toggle(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@Valid @PathVariable("id") Long id){
        taskStorage.delete(id);
    }

}
