package com.example.springwordle.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Word {
    @Id
    private String id;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public String getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}