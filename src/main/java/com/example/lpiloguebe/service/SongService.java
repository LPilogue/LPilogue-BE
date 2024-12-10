package com.example.lpiloguebe.service;

import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;

    @Transactional
    public void updateMainSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("해당 곡이 없습니다."));

        song.updateType(SongType.MAIN);
        log.info("{} 대표곡 설정 완료", song.toString());
    }

    @Transactional
    public void updateLikeSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("해당 곡이 없습니다."));

        song.updateIsLiked(1);
        log.info("{} 좋아요 설정 완료", song.toString());
    }
}
