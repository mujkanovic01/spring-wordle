package com.example.springwordle.core.repository;

import com.example.springwordle.core.model.UserGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserGameRepository extends MongoRepository<UserGame, String> {
    Optional<UserGame> findByUserIdAndGameId(String userId, String gameId);
}