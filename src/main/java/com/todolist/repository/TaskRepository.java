package com.todolist.repository;

import com.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;


public interface TaskRepository extends JpaRepository<Task, Long> {

    Set<Task> findByUserId(Long userId);
}
