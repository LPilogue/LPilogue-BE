package com.example.lpiloguebe.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
public class DiaryRequestDTO {

    private LocalDateTime createdAt;
    private String content;
    private List<SongRequestDTO> songs;
    private String cocktailName;
}
