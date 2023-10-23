package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.model.UserGame;
import com.example.springwordle.core.repository.UserGameRepository;
import com.example.springwordle.rest.dto.UserGame.UserGameUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGameService {
    private final UserGameRepository userGameRepository;

    public UserGameService(UserGameRepository userGameRepository) {
        this.userGameRepository = userGameRepository;
    }

    public List<UserGame> getUserGames() {
        List<UserGame> userGames = userGameRepository.findAll();

        return new ArrayList<>(userGames);
    }

    public UserGame getUserGameById(String id) {
        Optional<UserGame> userGame = userGameRepository.findById(id);
        return userGame.orElseThrow(() -> new ResourceNotFoundException("The User-Game with the given ID does not exist."));
    }

    public UserGame addUserGame(UserGame userGame) {
        return userGameRepository.save(userGame);
    }

    public UserGame updateUserGame(String id, UserGameUpdateDTO payload) {
        UserGame userGame = this.getUserGameById(id);

        if (payload.getHasWon() != null) {
            userGame.setHasWon(payload.getHasWon());
        }

        if (payload.getNumOfGuesses() != null) {
            userGame.setNumOfGuesses(payload.getNumOfGuesses());
        }

        return userGameRepository.save(userGame);
    }

    public void deleteUserGame(String id) {
        UserGame userGame = this.getUserGameById(id);
        userGameRepository.delete(userGame);
    }

}
