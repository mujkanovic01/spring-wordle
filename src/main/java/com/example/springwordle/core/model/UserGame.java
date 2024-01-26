package com.example.springwordle.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserGame {
    @Id
    private String id;
    private Integer numOfGuesses;
    private Boolean hasWon;
    private String userId; // FK to User
    private String gameId; // FK to Game

    public UserGame(Integer numOfGuesses, Boolean hasWon, String userId, String gameId) {
        this.setNumOfGuesses(numOfGuesses);
        this.setHasWon(hasWon);
        this.setUserId(userId);
        this.setGameId(gameId);
    }

    public String getId() {
        return id;
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