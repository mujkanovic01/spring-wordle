package com.example.springwordle.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class UserGame {
    @Id
    private String id;
    private Boolean hasWon;
    private List<String> guesses;
    private String userId; // FK to User
    private String gameId; // FK to Game

    public UserGame() {
        this.setHasWon(false);
        this.setUserId(null);
        this.setGameId(null);
        this.setGuesses(new ArrayList<>());
    }

    public UserGame(String gameId) {
        this.setHasWon(false);
        this.setUserId(null);
        this.setGameId(gameId);
        this.setGuesses(new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public Integer getNumOfGuesses() {
        return this.getGuesses().size();
    }

    public List<String> getGuesses() {
        return guesses;
    }

    public void addAGuess(String guess) {
        this.guesses.add(guess);
    }

    public void setGuesses(List<String> guesses) {
        this.guesses = guesses;
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