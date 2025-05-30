package com.example.lpiloguebe.service;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.SongResponseDTO;
import com.example.lpiloguebe.entity.Song;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.exception.GeneralException;
import com.example.lpiloguebe.repository.SongRepository;
import com.example.lpiloguebe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Song updateMainSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SONG_NOT_FOUND));

        song.updateType(SongType.MAIN);
        return songRepository.save(song);

    }

    @Transactional
    public Song updateLikeSong(Long songId) {
        Song song = songRepository.findById(songId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.SONG_NOT_FOUND));

        song.updateIsLiked(1);
        return songRepository.save(song);
    }

    public List<Song> getDislikeSong() {

        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        List<Song> unlikedSongList = songRepository.findUnlikedSongListByUsername(username);
        log.info("싫어요한 곡 리스트: {}", unlikedSongList.toString());


        return unlikedSongList;
    }
}
