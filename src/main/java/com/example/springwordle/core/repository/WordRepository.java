package com.example.springwordle.core.repository;

import com.example.springwordle.core.model.User;
import com.example.springwordle.core.model.Word;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends MongoRepository<Word, String> {

    @Aggregation(pipeline = { "{ $sample: { size: 1 } }" })
    List<Word> findRandomWord();

    Optional<Word> findByWord(String word);
}