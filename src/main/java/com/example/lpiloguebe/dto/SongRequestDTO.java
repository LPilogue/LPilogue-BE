package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.SongType;
import lombok.Data;
import lombok.Getter;

@Data
public class SongRequestDTO {

    private String name;
    private String artist;
    private String songURI;
    private String filePath;
    private int isLiked;
    private SongType type;
}
