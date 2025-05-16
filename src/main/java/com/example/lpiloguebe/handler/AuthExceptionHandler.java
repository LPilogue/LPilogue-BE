package com.example.lpiloguebe.handler;

import com.example.lpiloguebe.controller.AuthController;
import com.example.lpiloguebe.dto.ErrorResponseDTO;
import com.example.lpiloguebe.enumeration.ErrorCode;
import com.example.lpiloguebe.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = AuthController.class)
public class AuthExceptionHandler {

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameExists(UsernameAlreadyExistsException ex) {
        ErrorCode errorCode = ErrorCode.USERNAME_ALREADY_EXISTS;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>( errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUsernameNotFound(UsernameNotFoundException ex){
        ErrorCode errorCode = ErrorCode.USERNAME_NOT_FOUND;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>( errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDTO> handleBandCredentials(BadCredentialsException ex){
        ErrorCode errorCode = ErrorCode.BAD_PASSWORD;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>( errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidUsername(InvalidUsernameException ex){
        ErrorCode errorCode = ErrorCode.INVALID_USERNAME;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>( errorResponse, errorCode.getStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidPassword(InvalidPasswordException ex){
        ErrorCode errorCode = ErrorCode.INVALID_PASSWORD;
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .errorCode(errorCode.getErrorCode())
                .message(errorCode.getMessage())
                .build();
        return new ResponseEntity<>( errorResponse, errorCode.getStatus());
    }
}
