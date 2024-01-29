package com.example.springwordle.rest.dto.UserGame;

import java.util.List;

public class UserGameUpdateDTO {
    private Boolean hasWon;
    private List<String> guesses;

    public UserGameUpdateDTO() { }

    public UserGameUpdateDTO(Boolean hasWon, List<String> guesses) {
        this.setHasWon(hasWon);
        this.setGuesses(guesses);
    }

    public Boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(Boolean hasWon) {
        this.hasWon = hasWon;
    }

    public List<String> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<String> guesses) {
        this.guesses = guesses;
    }
}
