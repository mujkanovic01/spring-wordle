package com.example.springwordle.core.service;

import com.example.springwordle.core.model.User;
import com.example.springwordle.core.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        // List<User> users = userRepository.findAllCustom();

        return new ArrayList<>(users);
    }

    public User addUser(User payload) {
        return userRepository.save(payload);
    }
}
