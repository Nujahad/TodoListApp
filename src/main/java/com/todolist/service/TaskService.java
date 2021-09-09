package com.todolist.service;

import com.todolist.model.Status;
import com.todolist.model.Task;

import java.util.Set;

public interface TaskService {

    Set<Task> getTasksByUserId(Long userId);

    Task getTask(Long taskId);

    Task createTask(Task task, Long userId);

    Task updateTask(Task task, Long userId);

    void deleteTask(Long taskId, Long userId);

    Task changeStatus(Long taskId, Status status, Long userId);
}
