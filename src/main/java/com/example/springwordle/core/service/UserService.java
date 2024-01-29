package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.model.User;
import com.example.springwordle.core.repository.UserRepository;
import com.example.springwordle.rest.dto.User.UserUpdateDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        return new ArrayList<>(users);
    }

    public User getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("The user with the given ID does not exist."));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, UserUpdateDTO payload) {
        User user = this.getUserById(id);
        if (payload.getUsername() != null) {
            user.setUsername(payload.getUsername());
        }

        if (payload.getPassword() != null) {
            user.setPassword(payload.getPassword());
        }

        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        User user = this.getUserById(id);
        userRepository.delete(user);
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
