package com.example.springwordle.rest.dto.Game;

import java.util.Date;

public class GameUpdateDTO {
    private String word;
    private Date gameDate;

    public GameUpdateDTO() { }

    public GameUpdateDTO(String word, Date gameDate) {
        this.word = word;
        this.gameDate = gameDate;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}
