package com.example.springwordle.rest.dto.User;

import com.example.springwordle.core.model.User;

public class UserDTO {
//    TODO: Implement this class to prevent sending Passwords during Gets
//    Will implement after implementing password hashing

    private String id;
    private String username;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public String getUsername() {
        return username;
    }

    public String getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(String id) {
        this.id = id;
    }
}
