package com.example.springwordle.rest.controllers;

import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.service.GameService;
import com.example.springwordle.rest.dto.Game.GameCreateDTO;
import com.example.springwordle.rest.dto.Game.GameDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/games")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<Game>> getGames() {
        return ResponseEntity.ok(gameService.getGames());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/daily")
    public ResponseEntity<GameDTO> getDailyGame() {
        return ResponseEntity.ok(gameService.getTodayDailyGame());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Game> createGame(@RequestBody GameCreateDTO game) {
        return ResponseEntity.ok(gameService.addGame(game.toGame()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create-daily")
    public ResponseEntity<Game> createDailyGame() {
        return ResponseEntity.ok(gameService.createDailyGame());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        gameService.deleteGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
