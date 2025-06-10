package com.example.lpiloguebe.converter;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.exception.GeneralException;

import java.util.List;

public class DiaryConverter {

    public static DiaryResponseDTO.getDiaryPreviewListDTO toDiaryPreviewListDTO(List<Diary> diaryList){
        return DiaryResponseDTO.getDiaryPreviewListDTO.builder()
                .diaryPreviewList(diaryList.stream()
                        .map(DiaryConverter::toDiaryPreviewDTO)
                        .toList())
                .totalElements(diaryList.size())
                .build();
    }

    public static DiaryResponseDTO.getDiaryPreviewDTO toDiaryPreviewDTO(Diary diary) {
        return DiaryResponseDTO.getDiaryPreviewDTO.builder()
                .createdAt(diary.getCreatedAt())
                .songImagePath(String.valueOf(diary.getDiarySongList().stream()
                        .filter(diarySong -> diarySong.getSong().getType() == SongType.MAIN)
                        .findFirst()
                        .map(diarySong -> diarySong.getSong().getImagePath())
                        .orElseThrow(() -> new GeneralException(ErrorStatus.MAIN_SONG_NOT_FOUND))))
                .diaryId(diary.getId())
                .build();

    }

    public static DiaryResponseDTO.getDiaryDetailDTO toDiaryDetailDTO(Diary diary) {
        Song mainSong = diary.getDiarySongList()
                .stream()
                .filter(diarySong -> diarySong.getSong().getType() == SongType.MAIN)
                .findFirst()
                .orElseThrow(() -> new GeneralException(ErrorStatus.MAIN_SONG_NOT_FOUND))
                .getSong();

        return DiaryResponseDTO.getDiaryDetailDTO.builder()
                .createdAt(diary.getCreatedAt())
                .content(diary.getContent())
                .songURI(mainSong.getSongURI())
                .songName(mainSong.getName())
                .artist(mainSong.getArtist())
                .songImagePath(mainSong.getImagePath())
                .cocktailName(diary.getDiary_cocktail().getCocktail().getName())
                .cocktailImagePath(diary.getDiary_cocktail().getCocktail().getImagePath())
                .build();
    }

    public static DiaryResponseDTO.diarySongDTO toDiarySongDTO(Song song) {
        return DiaryResponseDTO.diarySongDTO.builder()
                .songId(song.getId())
                .songURI(song.getSongURI())
                .songName(song.getName())
                .artist(song.getArtist())
                .songImagePath(song.getImagePath())
                .build();
    }

    public static DiaryResponseDTO.diarySongListDTO toDiarySongListDTO(List<Song> songList) {
        return DiaryResponseDTO.diarySongListDTO.builder()
                .diarySongList(songList.stream()
                        .map(DiaryConverter::toDiarySongDTO)
                        .toList())
                .build();
    }

}
