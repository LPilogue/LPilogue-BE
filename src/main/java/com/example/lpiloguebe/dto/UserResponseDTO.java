package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.EmotionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

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

    @Builder
    @Getter
    public static class checkUsernameResultDTO {
        private String username;
        private boolean isExists;
        private String message;
    }

    @Builder
    @Getter
    public static class checkUsernamePasswordResultDTO {
        private String username;
        private String password;
        private String message;
    }

    @Builder
    @Getter
    public static class userUpdateResultDTO {
        private int happy;  // 기분이 좋을 때 더 듣고싶은 음악 1: 신나는 업템포 음악 0: 잔잔한 분위기의 음악
        private int sad;    // 우울할 때 찾게 되는 음악 1: 슬픔을 더 느낄 수 있는 감성적인 음악 0: 기분을 바꿔줄 밝은 음악
        private int stressed;   // 스트레스를 받을 때 더 끌리는 음악 1: 강렬한 비트와 강한 사운드의 음악 0: 차분하고 편안한 분위기의 음악
        private int lonely;     // 외로움을 느낄 때 더 듣고싶은 음악 1: 공감할 수 있는 가사와 감성적인 음악 0: 신나는 위로의 음악
        private String artist;  // 좋아하는 아티스트
    }


}
