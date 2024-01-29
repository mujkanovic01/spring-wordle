package com.example.springwordle.rest.dto.UserGame;

import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.UserGame;
import com.example.springwordle.core.helpers.print;
import com.example.springwordle.core.service.GameService;

import java.util.Date;

public class UserGameCreateDTO {
    private String userId; // FK to User

    public UserGameCreateDTO() {}

    public UserGameCreateDTO(UserGame game) {
        this.setUserId(game.getUserId());
    }

    public UserGame toUserGame() {
        return new UserGame(userId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}