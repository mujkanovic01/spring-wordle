package com.example.springwordle.rest.controllers;

import com.example.springwordle.core.model.UserGame;
import com.example.springwordle.core.service.UserGameService;
import com.example.springwordle.rest.dto.UserGame.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/userGames")
public class UserGameController {
    private final UserGameService userGameService;

    public UserGameController(UserGameService userGameService) {
        this.userGameService = userGameService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<UserGame>> getUserGames() {
        return ResponseEntity.ok(userGameService.getUserGames());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/startGame")
    public ResponseEntity<UserGame> startGame(@RequestBody UserGameCreateDTO userGame) {
        return ResponseEntity.ok(userGameService.startGame(userGame.toUserGame()));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/startDailyGame")
    public ResponseEntity<UserGame> startDailyGame(@RequestBody UserGameCreateDailyDTO userGame) {
        return ResponseEntity.ok(userGameService.startDailyGame(userGame.toUserGame()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<UserGame> getUserGameById(@PathVariable String id) {
        return ResponseEntity.ok(userGameService.getUserGameById(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/makeAGuess/{id}")
    public ResponseEntity<UserGameGuessResponseDTO> makeAGuess(@PathVariable String id, @RequestBody UserGameMakeAGuessDTO userGame) {
        return ResponseEntity.ok(userGameService.makeAGuess(id, userGame));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<UserGame> updateUserGame(@PathVariable String id, @RequestBody UserGameUpdateDTO userGame) {
        return ResponseEntity.ok(userGameService.updateUserGame(id, userGame));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteUserGame(@PathVariable String id) {
        userGameService.deleteUserGame(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
