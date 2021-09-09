package com.todolist.rest;

import com.todolist.model.Status;
import com.todolist.model.Task;
import com.todolist.service.TaskService;
import com.todolist.springboot.main.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@RestController
@RequestMapping("api/tasks/")
public class TaskListController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<Set<Task>> getTaskListByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId  =((UserDetailsImpl) userDetails).getId();
        Set<Task> taskSet = taskService.getTasksByUserId(userId);
        return ResponseEntity.ok(taskSet);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTask(@PathVariable Long taskId) {
        Task task = taskService.getTask(taskId);
        return ResponseEntity.ok(task);

    }

    @PostMapping()
    public ResponseEntity<Task> createTask(@Valid @NotNull @RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId  =((UserDetailsImpl) userDetails).getId();
        task = taskService.createTask(task, userId);
        return ResponseEntity.ok(task);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId  =((UserDetailsImpl) userDetails).getId();
        Task updatedTask = taskService.updateTask(task, userId);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId  =((UserDetailsImpl) userDetails).getId();
        taskService.deleteTask(taskId, userId);
        return ResponseEntity.ok().build();

    }

    @PatchMapping("/{taskId}")
    public ResponseEntity<Task> changeStatus(@PathVariable Long taskId, @RequestBody Status status, @AuthenticationPrincipal UserDetails userDetails) {
        Long userId  =((UserDetailsImpl) userDetails).getId();
        Task updatedTask = taskService.changeStatus(taskId, status, userId);
        return ResponseEntity.ok(updatedTask);

    }

}
