package com.example.springwordle.rest.dto.UserGame;

public class UserGameMakeAGuessDTO {
    private String guess;

    public UserGameMakeAGuessDTO() { }

    public UserGameMakeAGuessDTO(String guess, String userGameId) {
        this.setGuess(guess);
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}
