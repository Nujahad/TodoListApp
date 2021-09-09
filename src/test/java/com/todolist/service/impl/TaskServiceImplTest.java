package com.todolist.service.impl;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.model.Status;
import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.repository.TaskRepository;
import com.todolist.repository.UserRepository;
import com.todolist.service.TaskService;
import com.todolist.service.UserService;
import org.hibernate.mapping.Any;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    UserService userService;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService = new TaskServiceImpl();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void shouldReturnTaskSetByUserId() {
        //given
        Long userId = 1l;
        Set<Task> taskSet = new HashSet<>();
        taskSet.add(new Task(1L, "test", "test", Status.INPROGRESS, Calendar.getInstance()));

        //when
        when(taskRepository.findByUserId(userId)).thenReturn(taskSet);

        //then
        Set<Task> returnedSet = taskService.getTasksByUserId(1L);
        Assert.assertEquals(taskSet,returnedSet);
        verify(taskRepository, Mockito.times(1)).findByUserId(userId);
        verifyNoMoreInteractions(taskRepository);

    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUserNotFound() throws Exception{
        //given
        Long userId = 1L;

        //when
       when(userService.findById(userId)).thenReturn(Optional.empty());

        //
        exceptionRule.expect(EntityNotFoundException.class);
        exceptionRule.expectMessage("User id is invalid");
        taskService.createTask(any(Task.class), userId);
        verifyNoMoreInteractions(taskRepository);
    }

    @Test
    public void shouldCreateTask() {
        //given
        Long userId = 1L;
        User user = new User(userId,"test", "test");
        Task task = new Task(1L, "test", "test", Status.INPROGRESS, Calendar.getInstance());

        //when
        when(userService.findById(user.getId())).thenReturn(Optional.of(user));
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        when(taskRepository.save(captor.capture())).thenReturn(task);

        //then
        taskService.createTask(task,userId);
        Assert.assertEquals(task.getUser(),user);

    }


}