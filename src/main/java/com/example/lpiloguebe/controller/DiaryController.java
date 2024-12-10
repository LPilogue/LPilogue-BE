package com.example.lpiloguebe.controller;


import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;


    @PostMapping("")
    public ResponseEntity<?> createDiary(@RequestBody DiaryRequestDTO diaryRequestDTO) {

        if(diaryRequestDTO == null) {
            return new ResponseEntity<>("입력한 파라미터가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        diaryService.createDiary(diaryRequestDTO);
        return new ResponseEntity<>("일기 작성 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return new ResponseEntity<>("일기 삭제 완료", HttpStatus.OK);
    }
}
