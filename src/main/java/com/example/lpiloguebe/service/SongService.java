package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.SongRateDTO;
import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.entity.User;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.repository.SongRepository;
import com.example.lpiloguebe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;

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

    public List<SongRateDTO> getDislikeSong() {


        List<Song> songList = songRepository.findByUser("test");
        log.info("싫어요한 곡 리스트: {}", songList.toString());

        List<SongRateDTO> songRateDTOList = new ArrayList<>();
        songList.forEach(song -> {
            songRateDTOList.add(SongRateDTO.builder()
                    .name(song.getName())
                    .artist(song.getArtist())
                    .build());
        });
        log.info("싫어요한 곡 DTO 리스트: {}", songRateDTOList.toString());
        return songRateDTOList;
    }
}
