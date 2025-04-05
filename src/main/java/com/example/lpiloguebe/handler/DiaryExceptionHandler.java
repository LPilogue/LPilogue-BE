package com.example.lpiloguebe.handler;

import com.example.lpiloguebe.controller.DiaryController;
import com.example.lpiloguebe.exception.IllegalDateException;
import com.example.lpiloguebe.exception.IllegalDiaryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = DiaryController.class)
public class DiaryExceptionHandler {

    @ExceptionHandler(IllegalDateException.class)
    public ResponseEntity<?> handleIllegalDateException(IllegalDateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalDiaryException.class)
    public ResponseEntity<?> handleIllegalDiaryException(IllegalDiaryException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
