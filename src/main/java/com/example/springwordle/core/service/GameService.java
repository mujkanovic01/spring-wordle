package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.Word;
import com.example.springwordle.core.repository.GameRepository;
import com.example.springwordle.rest.dto.Game.GameDTO;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final WordService wordService;

    public GameService(GameRepository gameRepository, WordService wordService) {
        this.gameRepository = gameRepository;
        this.wordService = wordService;
    }

    public List<Game> getGames() {
        List<Game> games = gameRepository.findAll();
        return new ArrayList<>(games);
    }

    public Game getGameById(String id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(() -> new ResourceNotFoundException("The game with the given ID does not exist."));
    }

    public GameDTO getDailyGame() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datePart = sdf.format(currentDate);
        List<Game> games = gameRepository.findTodayDailyGames(datePart);

        if(games.isEmpty()) {
            throw new ResourceNotFoundException("No daily games found.");
        }

        return new GameDTO(games.get(0));
    }

    public Game createGame(Word word, Date date, Boolean isDaily) {
        Game newGame = new Game(word, date, isDaily);
        return this.addGame(newGame);
    }

    public Game createDailyGame() {
        Word word = this.wordService.getRandomWord();
        Game newGame = this.createGame(word, new Date(), true);
        return this.addGame(newGame);
    }

    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    public void deleteGame(String id) {
        Game game = this.getGameById(id);
        gameRepository.delete(game);
    }

}
