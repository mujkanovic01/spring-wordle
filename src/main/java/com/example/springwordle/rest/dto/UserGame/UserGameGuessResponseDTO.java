package com.example.springwordle.rest.dto.UserGame;

public class UserGameGuessResponseDTO {
    private Object[] letters;
    private boolean hasWon;

    public UserGameGuessResponseDTO() { }

    public UserGameGuessResponseDTO(Object[] letters, boolean hasWon) {
        this.setLetters(letters);
        this.setHasWon(hasWon);
    }

    public Object[] getLetters() {
        return letters;
    }

    public void setLetters(Object[] letters) {
        this.letters = letters;
    }

    public boolean getHasWon() {
        return hasWon;
    }

    public void setHasWon(boolean hasWon) {
        this.hasWon = hasWon;
    }
}
