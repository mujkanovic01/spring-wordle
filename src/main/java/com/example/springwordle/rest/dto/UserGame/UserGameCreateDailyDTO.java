package com.example.springwordle.rest.dto.UserGame;

import com.example.springwordle.core.model.UserGame;

public class UserGameCreateDailyDTO {
    private String userId; // FK to User
    private String gameId; // FK to Game

    public UserGameCreateDailyDTO() {}

    public UserGameCreateDailyDTO(UserGame game) {
        this.setUserId(game.getUserId());
        this.setGameId(game.getGameId());
    }

    public UserGame toUserGame() {
        return new UserGame(userId, gameId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}