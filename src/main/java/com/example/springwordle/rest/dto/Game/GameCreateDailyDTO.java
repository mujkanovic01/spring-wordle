package com.example.springwordle.rest.dto.Game;

import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.Word;

import java.util.Date;

public class GameCreateDailyDTO {
    private Word word;
    private Date gameDate;
    private Boolean isDaily;

    public GameCreateDailyDTO() { }

    public GameCreateDailyDTO(Game game) {
        this.setWord(game.getWord());
        this.setGameDate(game.getGameDate());
        this.setIsDaily(game.getIsDaily());
    }

    public Game toGame() {
        return new Game(word, gameDate, isDaily);
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

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Boolean getIsDaily() {
        return isDaily;
    }

    public void setIsDaily(Boolean isDaily) {
        this.isDaily = isDaily;
    }
}