package com.example.springwordle.rest.dto.UserGame;

import com.example.springwordle.core.model.UserGame;

public class UserGameCreateDTO {
    private Integer numOfGuesses;
    private Boolean hasWon;
    private String userId; // FK to User
    private String gameId; // FK to Game

    public UserGameCreateDTO() { }

    public UserGameCreateDTO(UserGame game) {
        this.numOfGuesses = game.getNumOfGuesses();
        this.hasWon = game.getHasWon();
        this.userId = game.getUserId();
        this.gameId = game.getGameId();
    }

    public UserGame toUserGame() {
        return new UserGame(numOfGuesses, hasWon, userId, gameId);
    }

    public Integer getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(Integer numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
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