package com.todolist.service;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.model.User;


import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long userId);
    User loadUserByUserName(String userName) throws EntityNotFoundException;

}
