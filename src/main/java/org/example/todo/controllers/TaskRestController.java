package org.example.todo.controllers;

import lombok.RequiredArgsConstructor;
import org.example.todo.entities.Task;
import org.example.todo.servises.TaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
public class TaskRestController {

    private final TaskService taskService;

    @GetMapping(path = "/list", produces = "application/json")
    @PreAuthorize("hasAuthority('task:all')")
    public List<Task> findAll(@RequestParam(name = "all", required = false) boolean all,
                              @RequestParam(name = "searchString", required = false) String searchString,
                              Principal principal) {
        return taskService.getList(all, searchString, principal);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('task:all')")
    public Task add(@Valid @RequestBody Task task, Principal principal){
        taskService.add(task, principal);
        return task;
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('task:all')")
    public Task updateTask(@Valid @RequestBody Task task, Principal principal){
        return taskService.edit(task, principal);
    }

    @PatchMapping(path = "/toggle", produces = "application/json")
    @PreAuthorize("hasAuthority('task:all')")
    public void toggle(@Valid @RequestParam("id") Long id, Principal principal){
        taskService.toggle(id, principal);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('task:all')")
    public void deleteById(@Valid @PathVariable("id") Long id, Principal principal){
        taskService.delete(id, principal);
    }

}
