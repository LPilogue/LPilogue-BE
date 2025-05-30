package com.example.lpiloguebe.converter;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.exception.GeneralException;

import java.util.List;

public class DiaryConverter {

    public static DiaryResponseDTO.getDiaryPreviewListDTO toDiaryPreviewListDTO(List<Diary> diaryList){
        return DiaryResponseDTO.getDiaryPreviewListDTO.builder()
                .diaryPreviewList(diaryList.stream()
                        .map(DiaryConverter::toDiaryPreviewDTO)
                        .toList())
                .build();
    }

    public static DiaryResponseDTO.getDiaryPreviewDTO toDiaryPreviewDTO(Diary diary) {
        return DiaryResponseDTO.getDiaryPreviewDTO.builder()
                .createdAt(diary.getCreatedAt())
                .songImagePath(String.valueOf(diary.getDiarySongList().stream()
                        .filter(diarySong -> diarySong.getSong().getType() == SongType.MAIN)
                        .findFirst()
                        .orElseThrow(() -> new GeneralException(ErrorStatus.MAIN_SONG_NOT_FOUND))))
                .build();

    }
}
