package com.example.springwordle.rest.dto.Game;

import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.Word;

import java.util.Date;

public class GameDTO {
    private String id;
    private Date gameDate;
    private Boolean isDaily;

    public GameDTO() { }

    public GameDTO(String id, Date gameDate, Boolean isDaily) {
        this.setId(id);
        this.setGameDate(gameDate);
        this.setIsDaily(isDaily);
    }

    public GameDTO(Game game) {
        this.setId(game.getId());
        this.setGameDate(game.getGameDate());
        this.setIsDaily(game.getIsDaily());
    }

    public GameDTO(GameDTO game) {
        this.setId(game.getId());
        this.setGameDate(game.getGameDate());
        this.setIsDaily(game.getIsDaily());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
