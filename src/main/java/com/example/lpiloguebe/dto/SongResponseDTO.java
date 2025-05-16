package com.example.lpiloguebe.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SongResponseDTO {

    private String name;
    private String artist;

    @Builder
    public SongResponseDTO(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }
}
