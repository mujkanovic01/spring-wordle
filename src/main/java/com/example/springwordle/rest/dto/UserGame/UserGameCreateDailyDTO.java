package com.example.springwordle.rest.dto.UserGame;

import com.example.springwordle.core.helpers.print;
import com.example.springwordle.core.model.UserGame;

public class UserGameCreateDailyDTO {
    private String gameId; // FK to Game

    public UserGameCreateDailyDTO() {}

    public UserGameCreateDailyDTO(UserGame game) {
        this.setGameId(game.getGameId());
    }

    public UserGame toUserGame() {
        return new UserGame(gameId);
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}