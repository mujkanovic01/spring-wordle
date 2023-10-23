package com.example.springwordle.rest.dto.Game;

import com.example.springwordle.core.model.Game;

import java.util.Date;

public class GameCreateDTO {
    private String word;
    private Date gameDate;

    public GameCreateDTO() { }

    public GameCreateDTO(Game game) {
        this.word = game.getWord();
        this.gameDate = game.getGameDate();
    }

    public Game toGame() {
        return new Game(word, gameDate);
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

    public void getGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}