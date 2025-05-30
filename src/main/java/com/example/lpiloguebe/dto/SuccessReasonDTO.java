package com.example.lpiloguebe.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class SuccessReasonDTO {
    private HttpStatus httpStatus;
    private final boolean isSuccess;
    private final String code;
    private final String message;
}
