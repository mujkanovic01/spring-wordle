package com.example.springwordle.core.exceptions.game;

import com.example.springwordle.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GameAlreadyFinished extends GeneralException {
    public GameAlreadyFinished(String message) {
        super(HttpStatus.BAD_REQUEST.value(), message);
    }

    public GameAlreadyFinished() {
        super(HttpStatus.BAD_REQUEST.value());
    }
}