package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.entity.Diary_song;
import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.repository.DiaryRepository;
import com.example.lpiloguebe.repository.Diary_songRepository;
import com.example.lpiloguebe.repository.SongRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Diary_songService {

    private final Diary_songRepository diary_songRepository;

    private final SongRepository songRepository;
    private final DiaryRepository diaryRepository;

    /**
     * 일기와 노래를 연결하는 메서드
     * @param diary
     * @param diaryRequestDTO
     * @return diary
     */
    public Diary addSongToDiary(Diary diary, DiaryRequestDTO diaryRequestDTO) {

        if (diaryRequestDTO.getSongs() == null) {
            throw new IllegalArgumentException("노래 정보가 없습니다.");
        }
        /*
        DTO에서 노래 정보 가져와서 저장
        Diary_song 테이블에 일기와 노래 정보 연결
        Diary 테이블에 Diary_song 리스트 추가
         */
        diaryRequestDTO.getSongs()
                .forEach(songRequestDTO -> {
                    Song song= Song.builder()
                            .artist(songRequestDTO.getArtist())
                            .imagePath(songRequestDTO.getImagePath())
                            .isLiked(0)
                            .name(songRequestDTO.getName())
                            .songURI(songRequestDTO.getSongURI())
                            .type(songRequestDTO.getType())
                            .build();
                    log.info("노래 정보: {}", song.toString());
                    songRepository.save(song);
                    log.info("노래 저장 완료");
                    Diary_song diary_song= Diary_song.builder()
                            .diary(diary)
                            .song(song)
                            .build();
                    log.info("Diary_song 정보: {}", diary_song.toString());
                    diary_songRepository.save(diary_song);
                    log.info("Diary_song 저장 완료");
                    diary.getDiarySongList().add(diary_song);

                    log.info("일기 {}의 노래 리스트: {}", diary.getId(), diary.getDiarySongList().stream());

                });
        return diaryRepository.save(diary);
    }
}
