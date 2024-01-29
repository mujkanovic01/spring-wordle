package com.example.springwordle.rest.dto.User;

import com.example.springwordle.core.model.User;

public class UserRegisterDTO {
    private String username;
    private String password;
    private String confirmPassword;

    public UserRegisterDTO() { }

    public UserRegisterDTO(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public User toEntity() {
        return new User(username, password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}