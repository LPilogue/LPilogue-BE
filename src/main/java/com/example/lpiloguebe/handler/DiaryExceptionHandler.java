package com.example.lpiloguebe.handler;

import com.example.lpiloguebe.controller.DiaryController;
import com.example.lpiloguebe.exception.InvalidDateException;
import com.example.lpiloguebe.exception.DiaryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = DiaryController.class)
public class DiaryExceptionHandler {

    @ExceptionHandler(InvalidDateException.class)
    public ResponseEntity<?> handleIllegalDateException(InvalidDateException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DiaryNotFoundException.class)
    public ResponseEntity<?> handleIllegalDiaryException(DiaryNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
