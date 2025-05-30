package com.example.lpiloguebe.dto;

import com.example.lpiloguebe.enumeration.EmotionType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiaryRequestDTO {

    private LocalDateTime createdAt;
    private String content;
    private List<SongRequestDTO> songs;
    private String cocktailName;
    private EmotionType emotionType;
}
