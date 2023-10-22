package com.example.springwordle.rest.controllers;

import com.example.springwordle.core.model.User;
import com.example.springwordle.core.service.UserService;
import com.example.springwordle.rest.dto.UserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterDTO user) {
        return ResponseEntity.ok(userService.addUser(user.toUser()));
    }
}
