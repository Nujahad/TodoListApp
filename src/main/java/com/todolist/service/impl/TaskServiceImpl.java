package com.todolist.service.impl;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.model.Status;
import com.todolist.model.Task;
import com.todolist.repository.TaskRepository;
import com.todolist.service.TaskService;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Override
    public Set<Task> getTasksByUserId(Long userId) {
        if (userId != null) {
            return taskRepository.findByUserId(userId);
        } else {
            throw new EntityNotFoundException("User Id is invalid");
        }
    }

    @Override
    public Task getTask(Long taskId) {
        return taskRepository.findById(taskId).get();
    }

    @Override
    public Task createTask(Task task, Long userId) {
            userService.findById(userId).map(user -> {
            task.setUser(user);
            task.setStatus(Status.INPROGRESS);
            return task;
        }).orElseThrow(() -> new EntityNotFoundException("User id is invalid"));
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Task updateTask, Long userId) {
        if(updateTask.getId() == null) {
            throw new EntityNotFoundException("Task id is Mandatory");
        }
        Task task = taskRepository.findByIdAndUserId(updateTask.getId(), userId).map(task1 -> {
            task1.setDescription(updateTask.getDescription());
            task1.setName(updateTask.getName());
            task1.setStatus(updateTask.getStatus());
            return task1;
             }).orElseThrow(() -> new EntityNotFoundException("ask not found for given user"));
        return taskRepository.save(task);
    }


    @Override
    public void deleteTask(Long taskId, Long userId) {
        if(taskId == null) {
            throw new EntityNotFoundException("Task id is Mandatory");
        }
        Task task = taskRepository.findByIdAndUserId(taskId, userId).
                orElseThrow(() -> new EntityNotFoundException("Task not found"));
         taskRepository.delete(task);
    }

    @Override
    public Task changeStatus(Long taskId, Status status, Long userId) {
        if(taskId == null) {
            throw new EntityNotFoundException("Task id is Mandatory");
        }
        Task task = taskRepository.findByIdAndUserId(taskId, userId).map(task1 -> {
            task1.setStatus(status);
            return task1;
        }).orElseThrow(() -> new EntityNotFoundException("Task not found for given user"));
        return taskRepository.save(task);    }
}
