package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.Word;
import com.example.springwordle.core.repository.GameRepository;
import com.example.springwordle.rest.dto.Game.GameUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames() {
        List<Game> games = gameRepository.findAll();

        return new ArrayList<>(games);
    }

    public Game getGameById(String id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(() -> new ResourceNotFoundException("The game with the given ID does not exist."));
    }

    public Game createGame(Word word, Date date) {
        Game newGame = new Game(word, date);
        return this.addGame(newGame);
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

//    public Game updateGame(String id, GameUpdateDTO payload) {
//        Game game = this.getGameById(id);
//
//        if (payload.getWord() != null) {
//            game.setWord(payload.getWord());
//        }
//
//        if (payload.getGameDate() != null) {
//            game.setGameDate(payload.getGameDate());
//        }
//
//        return gameRepository.save(game);
//    }

    public void deleteGame(String id) {
        Game game = this.getGameById(id);
        gameRepository.delete(game);
    }

}
