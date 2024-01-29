package com.example.springwordle.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Game {
    @Id
    private String id;
    private Word word;
    private Date gameDate;
    private Boolean isDaily;

    public Game(Word word, Date gameDate, Boolean isDaily) {
        this.setWord(word);
        this.setGameDate(gameDate);
        this.setIsDaily(isDaily);
    }

    public String getId() {
        return this.id;
    }

    public Word getWord() {
        return this.word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Date getGameDate() {
        return this.gameDate;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public Boolean getIsDaily() {
        return this.isDaily;
    }

    public void setIsDaily(Boolean isDaily) {
        this.isDaily = isDaily;
    }
}