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

    public Game(Word word, Date gameDate) {
        this.setWord(word);
        this.setGameDate(gameDate);
    }

    public String getId() {
        return id;
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
}