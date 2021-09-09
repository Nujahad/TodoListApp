package com.todolist.service.impl;

import com.todolist.exception.EntityNotFoundException;
import com.todolist.model.User;
import com.todolist.repository.UserRepository;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findById(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId);
        }
        throw new EntityNotFoundException("User Id is Mandatory");
    }

    @Override
    public User loadUserByUserName(String username) throws EntityNotFoundException {
        Optional<User> user1 = userRepository.findByUserName("1");
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found with username: " + username));
        return  user;

    }
}
