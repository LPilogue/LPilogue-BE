package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.SongType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;


public class SongResponseDTO {

    @Builder
    @Getter
    public static class updateMainSongResultDTO {
        private Long songId; // 노래 ID
        private String title; // 노래 제목
        private SongType type; // 노래 타입
    }

    @Builder
    @Getter
    public static class updateLikeSongResultDTO {
        private Long songId; // 노래 ID
        private String title; // 노래 제목
        private int isLiked; // 좋아요 여부 (1: 좋아요, 0: 좋아요 취소)
    }

    @Builder
    @Getter
    public static class getSongResultDTO {
        private String title; // 노래 제목
        private String artist; // 아티스트 이름
    }

    @Builder
    @Getter
    public static class getSongListResultDTO {
        private List<getSongResultDTO> songList; // 노래 리스트
    }

    @Builder
    @Getter
    public static class mostFrequentArtistDTO {
        private String artist; // 아티스트 이름
        private Long count; // 해당 아티스트의 곡 수
    }

}
