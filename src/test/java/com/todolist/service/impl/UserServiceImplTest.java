package com.todolist.service.impl;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.model.User;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest{

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserServiceImpl();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldLoadByUserName() {
        //given
        User user = new User("test", "test");

        //when
        when(userRepository.findByUserName(user.getUserName())).thenReturn(java.util.Optional.of(user));

        //then
        User userReturned = userService.loadUserByUserName(user.getUserName());
        Assert.assertEquals(user.getUserName(),userReturned.getUserName());

    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUserNotFound() throws Exception{
        //given
        User user = new User("test", "test");

        //when
        when(userRepository.findByUserName(user.getUserName())).thenReturn(Optional.empty());

        //then
        exceptionRule.expect(EntityNotFoundException.class);
        exceptionRule.expectMessage("User Not Found with username: "+user.getUserName());
        userService.loadUserByUserName(user.getUserName());
    }

    @Test
    public void shouldThrowNotFoundExceptionWhenUserIdNull() {
        //given
        Long userId = null;

        //then
        exceptionRule.expect(EntityNotFoundException.class);
        exceptionRule.expectMessage("User Id is Mandatory");
        userService.findById(userId);
    }

    @Test
    public void shouldLoadByUserId() {
        //given
        User user = new User(1L,"test", "test");

        //when
        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));

        //then
        Optional<User> userReturned = userService.findById(user.getId());
        Assert.assertEquals(user.getId(),userReturned.get().getId());

    }


}