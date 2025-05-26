package com.example.lpiloguebe.controller;


import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.service.DiaryService;
import com.example.lpiloguebe.service.Diary_cocktailService;
import com.example.lpiloguebe.service.Diary_songService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    private final Diary_cocktailService diary_cocktailService;

    private final Diary_songService diary_songService;

    @PostMapping("")
    public ResponseEntity<?> createDiary(@RequestBody DiaryRequestDTO diaryRequestDTO) {


        Diary diary = diaryService.createDiary(diaryRequestDTO);
        diary = diary_cocktailService.addCocktailToDiary(diary, diaryRequestDTO);
        diary_songService.addSongToDiary(diary, diaryRequestDTO);
        return new ResponseEntity<>("일기 작성 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId) {

        diaryService.deleteDiary(diaryId);
        return new ResponseEntity<>("일기 " + diaryId + " 삭제 완료", HttpStatus.OK);
    }

    // 일기 리스트 조회
    @GetMapping("")
    public ResponseEntity<?> getDiaryList(@RequestParam int year, @RequestParam int month) {

        List<DiaryResponseDTO> diaryList = diaryService.getDiaryList(year, month);
        return new ResponseEntity<>(diaryList, HttpStatus.OK);
    }

}
