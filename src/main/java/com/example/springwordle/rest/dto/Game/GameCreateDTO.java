package com.example.springwordle.rest.dto.Game;

import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.Word;

import java.util.Date;

public class GameCreateDTO {
    private Word word;
    private Date gameDate;

    public GameCreateDTO() { }

    public GameCreateDTO(Game game) {
        this.word = game.getWord();
        this.gameDate = game.getGameDate();
    }

    public Game toGame() {
        return new Game(word, gameDate);
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public void getGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }
}