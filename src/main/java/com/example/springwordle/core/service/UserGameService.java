package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.GeneralException;
import com.example.springwordle.core.exceptions.game.GameAlreadyFinished;
import com.example.springwordle.core.exceptions.game.InvalidWordException;
import com.example.springwordle.core.exceptions.game.TooManyGuessesException;
import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.helpers.print;
import com.example.springwordle.core.model.Game;
import com.example.springwordle.core.model.User;
import com.example.springwordle.core.model.UserGame;
import com.example.springwordle.core.model.Word;
import com.example.springwordle.core.repository.UserGameRepository;
import com.example.springwordle.rest.dto.Game.GameDTO;
import com.example.springwordle.rest.dto.UserGame.UserGameGuessResponseDTO;
import com.example.springwordle.rest.dto.UserGame.UserGameMakeAGuessDTO;
import com.example.springwordle.rest.dto.UserGame.UserGameUpdateDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGameService {
    private final UserGameRepository userGameRepository;
    private final GameService gameService;
    private final WordService wordService;
    private final UserService userService;

    public UserGameService(UserGameRepository userGameRepository, GameService gameService, WordService wordService, UserService userService) {
        this.userGameRepository = userGameRepository;
        this.gameService = gameService;
        this.wordService = wordService;
        this.userService = userService;
    }

    public List<UserGame> getUserGames() {
        List<UserGame> userGames = userGameRepository.findAll();
        return new ArrayList<>(userGames);
    }

    public UserGame getUserGameById(String id) {
        Optional<UserGame> userGame = userGameRepository.findById(id);
        return userGame.orElseThrow(() -> new ResourceNotFoundException("The User-Game with the given ID does not exist."));
    }

    public UserGame getUserGameByUserIdAndGameId(String userId, String gameId) {
        Optional<UserGame> userGame = userGameRepository.findByUserIdAndGameId(userId, gameId);
        new print(userGame);
        return userGame.orElse(null);
    }

    public UserGame startGame(UserGame userGame) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUsername(authentication.getName());
        userGame.setUserId(user.getId());

        Word word = this.wordService.getRandomWord();
        Game newGame = this.gameService.createGame(word, new Date(), false);
        userGame.setGameId(newGame.getId());

        return userGameRepository.save(userGame);
    }

    public UserGame startDailyGame(UserGame userGame) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userService.getUserByUsername(authentication.getName());
        userGame.setUserId(user.getId());

        GameDTO dailyGame = this.gameService.getDailyGame();
        userGame.setGameId(dailyGame.getId());

        UserGame existingUserGame = this.getUserGameByUserIdAndGameId(user.getId(), userGame.getGameId());
        if(existingUserGame != null) throw new GeneralException("You cannot play the same game more than once.");

        return userGameRepository.save(userGame);
    }

    public UserGameGuessResponseDTO makeAGuess(String id, UserGameMakeAGuessDTO payload) {
        UserGame userGame = this.getUserGameById(id);
        String userGuess = payload.getGuess().toLowerCase();

        Game game = this.gameService.getGameById(userGame.getGameId());
        String wordToGuess = game.getWord().getWord();

        if(userGame.getHasWon()) {
            throw new GameAlreadyFinished("You have already finished this game.");
        }

        if(userGame.getNumOfGuesses() >= 6) {
            throw new TooManyGuessesException("You have already made 6 guesses.");
        }

        try {
            wordService.getWordByWord(userGuess);
        } catch (ResourceNotFoundException e) {
            throw new InvalidWordException("The guess is not a valid word.");
        };

        userGame.addAGuess(userGuess);
        userGameRepository.save(userGame);

        Object[] result = {null, null, null, null, null};
        char[] lettersToGuess = wordToGuess.toCharArray();
        char[] userLetters = userGuess.toCharArray();
        if (userGuess.equals(wordToGuess)) {
            userGame.setHasWon(true);
            userGameRepository.save(userGame);
            for(int i = 0; i < 5; i++) {
                result[i] = Map.of(userLetters[i], "HIT");
                lettersToGuess[i] = '/';
                userLetters[i] = '/';
            }
            return new UserGameGuessResponseDTO(result, userGame.getHasWon());
        }

        for(int i = 0; i < 5; i++) {
            if (userLetters[i] == lettersToGuess[i]) {
                result[i] = Map.of(userLetters[i], "HIT");
                lettersToGuess[i] = '/';
                userLetters[i] = '/';
            }
        }

        for(int i = 0; i < 5; i++) {
            if (userLetters[i] == '/') continue;

            if (new String(lettersToGuess).contains(String.valueOf(userLetters[i]))) {
                result[i] = Map.of(userLetters[i], "PARTIAL");
                lettersToGuess[new String(lettersToGuess).indexOf(userLetters[i])] = '/';
            } else {
                result[i] = Map.of(userLetters[i], "MISS");
            }
            userLetters[i] = '/';
        }

        new print(result);
        return new UserGameGuessResponseDTO(result, userGame.getHasWon());
    }

    public UserGame updateUserGame(String id, UserGameUpdateDTO payload) {
        UserGame userGame = this.getUserGameById(id);

        if (payload.getHasWon() != null) {
            userGame.setHasWon(payload.getHasWon());
        }

        return userGameRepository.save(userGame);
    }

    public void deleteUserGame(String id) {
        UserGame userGame = this.getUserGameById(id);
        userGameRepository.delete(userGame);
    }

}
