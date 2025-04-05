package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    // 대표곡 선정
    @PatchMapping("/main/{songId}")
    public ResponseEntity<?> updateMainSong(@PathVariable Long songId) {

        songService.updateMainSong(songId);
        return new ResponseEntity<>("노래 " + songId + " 대표곡 설정 완료", HttpStatus.OK);
    }

    // 곡에 좋아요
    @PatchMapping("/like/{songId}")
    public ResponseEntity<?> updateLikeSong(@PathVariable Long songId) {

        songService.updateLikeSong(songId);
        return new ResponseEntity<>("노래 " + songId + "에 좋아요 설정 완료", HttpStatus.OK);
    }

    // 싫어요한 곡 가져오기
    @GetMapping("/dislike")
    public ResponseEntity<?> getDislikeSong() {
        return new ResponseEntity<>(songService.getDislikeSong(), HttpStatus.OK);
    }
}
