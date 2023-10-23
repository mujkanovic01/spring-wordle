package com.example.springwordle.core.repository;

import com.example.springwordle.core.model.UserGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGameRepository extends MongoRepository<UserGame, String> { }