package com.example.lpiloguebe.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryResponseDTO {
    private LocalDateTime createdAt;
    private String content;
    private String songName;
    private String songURI;
    private String artist;
    private String songImagePath;
    private String cocktailName;
    private String cocktailImagePath;

    @Builder
    public DiaryResponseDTO(LocalDateTime createdAt, String content, String songName, String songURI, String artist, String songImagePath, String cocktailName, String cocktailImagePath) {
        this.createdAt = createdAt;
        this.content = content;
        this.songName = songName;
        this.songURI = songURI;
        this.artist = artist;
        this.songImagePath = songImagePath;
        this.cocktailName = cocktailName;
        this.cocktailImagePath = cocktailImagePath;
    }
}
