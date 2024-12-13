package com.example.lpiloguebe.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SongRateDTO {

    private String name;
    private String artist;

    @Builder
    public SongRateDTO(String name, String artist) {
        this.name = name;
        this.artist = artist;
    }
}
