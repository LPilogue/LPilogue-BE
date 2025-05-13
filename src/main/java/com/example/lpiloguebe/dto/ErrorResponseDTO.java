package com.example.lpiloguebe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponseDTO {
    private String errorCode;
    private String message;

    @Builder
    public ErrorResponseDTO(HttpStatus status, String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}
