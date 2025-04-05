package com.example.lpiloguebe.handler;

import com.example.lpiloguebe.controller.SongController;
import com.example.lpiloguebe.exception.IllegalSongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SongController.class)
public class SongExceptionHandler {

    @ExceptionHandler(IllegalSongException.class)
    public ResponseEntity<?> handleIllegalSongException(IllegalSongException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
