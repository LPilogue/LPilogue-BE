package com.example.lpiloguebe.controller;

import com.example.lpiloguebe.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    // 대표곡 선정
    @PatchMapping("/main/{songId}")
    public ResponseEntity<?> updateMainSong(@PathVariable Long songId) {
        if(songId == null) {
            throw new IllegalArgumentException("입력한 파라미터가 없습니다.");
        }
        songService.updateMainSong(songId);
        return new ResponseEntity<>(songId + " 대표곡 설정 완료", HttpStatus.OK);
    }
}
