package com.example.springwordle.core.service;

import com.example.springwordle.core.exceptions.repository.ResourceNotFoundException;
import com.example.springwordle.core.helpers.print;
import com.example.springwordle.core.model.Word;
import com.example.springwordle.core.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getWords() {
        List<Word> words = wordRepository.findAll();

        return new ArrayList<>(words);
    }

    public Word getWordById(String id) {
        Optional<Word> word = wordRepository.findById(id);
        return word.orElseThrow(() -> new ResourceNotFoundException("The word with the given ID does not exist."));
    }

    public Word getRandomWord() {
        List<Word> randomWordList = wordRepository.findRandomWord();

        if (randomWordList.isEmpty()) {
            throw new ResourceNotFoundException("No words available in the repository.");
        }

        return randomWordList.get(0);
    }

    public String getWordByWord(String userGuess) {
//        try {
            Optional<Word> word = wordRepository.findByWord(userGuess);

            if (word.isEmpty()) {
                throw new ResourceNotFoundException("The word with the given ID does not exist.");
            }

            return word.get().getWord();
//        } catch (Exception e) {
//            new print("Exception: " + e.getMessage());
//            throw new ResourceNotFoundException("The word with the given ID does not exist.");
//        }
    }
}
