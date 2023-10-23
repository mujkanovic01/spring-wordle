package com.example.springwordle.rest.dto.UserGame;

public class UserGameUpdateDTO {
    private Integer numOfGuesses;
    private Boolean hasWon;

    public UserGameUpdateDTO() { }

    public UserGameUpdateDTO(Integer numOfGuesses, Boolean hasWon) {
        this.numOfGuesses = numOfGuesses;
        this.hasWon = hasWon;
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
}
