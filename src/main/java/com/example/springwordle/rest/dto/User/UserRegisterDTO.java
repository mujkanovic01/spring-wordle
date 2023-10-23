package com.example.springwordle.rest.dto.User;

import com.example.springwordle.core.model.User;

public class UserRegisterDTO {
    private String username;
    private String email;
    private String password;

    public UserRegisterDTO() { }

    public UserRegisterDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public User toUser() {
        return new User(email, username, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}