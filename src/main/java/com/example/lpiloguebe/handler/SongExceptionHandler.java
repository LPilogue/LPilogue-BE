package com.example.lpiloguebe.handler;

import com.example.lpiloguebe.controller.SongController;
import com.example.lpiloguebe.dto.ErrorResponseDTO;
import com.example.lpiloguebe.enumeration.ErrorCode;
import com.example.lpiloguebe.exception.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SongController.class)
public class SongExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleSongNotFound(SongNotFoundException ex) {
        ErrorCode errorCode = ErrorCode.SONG_NOT_FOUND;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>(errorResponse, errorCode.getStatus());
    }
}
