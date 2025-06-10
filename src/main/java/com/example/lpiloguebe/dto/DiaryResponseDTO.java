package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.EmotionType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


public class DiaryResponseDTO {


    @Builder
    @Getter
    public static class getDiaryDetailDTO {
        private LocalDateTime createdAt;
        private String content;
        // 대표 곡만
        private String songName;
        private String songURI;
        private String artist;
        private String songImagePath;
        private String cocktailName;
        private String cocktailImagePath;
    }

    @Builder
    @Getter
    public static class getDiaryPreviewDTO {
        private Long diaryId;
        private LocalDateTime createdAt; // 일기 작성 시간
        private String songImagePath; // 대표 곡 이미지 경로
    }

    @Builder
    @Getter
    public static class getDiaryPreviewListDTO {
        private List<getDiaryPreviewDTO> diaryPreviewList; // 일기 리스트
        private int totalElements; // 전체 일기 개수
    }



    @Builder
    @Getter
    public static class deleteDiaryResultDTO {
        private Long diaryId;
        private String message;
    }

    @Builder
    @Getter
    public static class createDiaryResultDTO {
        private Long diaryId;
        private LocalDateTime createdAt;
        private String message;
    }

    @Builder
    @Getter
    public static class mostFrequentEmotionDTO {
        private EmotionType emotionType; // 감정 타입
        private Long count; // 해당 감정의 일기 개수
    }

    @Builder
    @Getter
    public static class diarySongDTO {
        private Long songId; // 곡 ID
        private String songName; // 곡 이름
        private String artist; // 아티스트 이름
        private String songURI; // 곡 URI
        private String songImagePath; // 곡 이미지 경로

    }

    @Builder
    @Getter
    public static class diarySongListDTO {
        private List<diarySongDTO> diarySongList; // 일기 곡 리스트

    }
}
