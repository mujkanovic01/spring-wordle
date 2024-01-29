package com.example.springwordle.core.repository;

import com.example.springwordle.core.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    @Query(value = "{ '$expr': { '$eq': [?0, { '$dateToString': { 'date': '$gameDate', 'format': '%Y-%m-%d' } }] }, 'isDaily': true }")
    List<Game> findTodayDailyGames(String datePart);
}