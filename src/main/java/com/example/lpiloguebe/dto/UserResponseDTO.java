package com.example.lpiloguebe.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserResponseDTO {

    @Builder
    @Getter
    public static class signupResultDTO {
        private long userId;
        private LocalDateTime createdAt;
    }

    @Builder
    @Getter
    public static class signInResultDTO {
        private String accessToken;

    }
}
