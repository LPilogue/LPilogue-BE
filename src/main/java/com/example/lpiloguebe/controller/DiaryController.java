package com.example.lpiloguebe.controller;


import com.example.lpiloguebe.apiPayload.code.ApiResponse;
import com.example.lpiloguebe.converter.DiaryConverter;
import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.enumeration.EmotionType;
import com.example.lpiloguebe.service.DiaryService;
import com.example.lpiloguebe.service.Diary_cocktailService;
import com.example.lpiloguebe.service.Diary_songService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    private final Diary_cocktailService diary_cocktailService;

    private final Diary_songService diary_songService;

    @PostMapping("")
    @Operation(summary = "일기 작성", description = "사용자가 일기를 작성할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 작성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @Parameters({
            @Parameter(name = "createdAt", description = "일기 작성 날짜 (2025-05-30T06:24:26.027Z 형식)"),
            @Parameter(name = "content", description = "일기 내용"),
            @Parameter(name = "songs", description = "일기에 추가할 노래 목록"),
            @Parameter(name="cocktailName", description = "일기에 추가할 칵테일 이름"),
            @Parameter(name = "emotionType", description = "일기 작성 시의 감정 상태 (HAPPY, SURPRISE, SAD, ANGRY, HURT, ANXIOUS)")
    })
    public ApiResponse<DiaryResponseDTO.createDiaryResultDTO> createDiary(@RequestBody DiaryRequestDTO diaryRequestDTO) {
        Diary diary = diaryService.createDiary(diaryRequestDTO);
        diary = diary_cocktailService.addCocktailToDiary(diary, diaryRequestDTO);
        diary_songService.addSongToDiary(diary, diaryRequestDTO);

        return ApiResponse.onSuccess(DiaryResponseDTO.createDiaryResultDTO.builder()
                .diaryId(diary.getId())
                .createdAt(diary.getCreatedAt())
                .message("일기 작성 성공")
                .build());
    }

    @DeleteMapping("/{diaryId}")
    @Operation(summary = "일기 삭제", description = "사용자가 작성한 일기를 삭제할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "일기를 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "diaryId", description = "삭제할 일기의 ID", required = true)
    })
    public ApiResponse<DiaryResponseDTO.deleteDiaryResultDTO> deleteDiary(@PathVariable Long diaryId) {

        diaryService.deleteDiary(diaryId);
        return ApiResponse.onSuccess(
                DiaryResponseDTO.deleteDiaryResultDTO.builder()
                        .diaryId(diaryId)
                        .message("일기 삭제 성공")
                        .build());
    }

    // 일기 리스트 조회
    @GetMapping("")
    @Operation(summary = "일기 리스트 조회", description = "사용자가 작성한 일기 리스트를 조회할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 리스트 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @Parameters({
            @Parameter(name = "year", description = "조회할 일기의 연도", required = true),
            @Parameter(name = "month", description = "조회할 일기의 월", required = true)
    })
    public ApiResponse<DiaryResponseDTO.getDiaryPreviewListDTO> getDiaryPreviewList(@RequestParam int year, @RequestParam int month) {

        List<Diary> diaryList = diaryService.getDiaryPreviewList(year, month);
        return ApiResponse.onSuccess(DiaryConverter.toDiaryPreviewListDTO(diaryList));

    }

    // 일기 상세 조회
    @GetMapping("/{diaryId}")
    @Operation(summary = "일기 상세 조회", description = "사용자가 작성한 일기의 상세 정보를 조회할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "일기 상세 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "일기를 찾을 수 없음")
    })
    @Parameters({
            @Parameter(name = "diaryId", description = "조회할 일기의 ID", required = true)
    })
    public ApiResponse<DiaryResponseDTO.getDiaryDetailDTO> getDiaryDetail(@PathVariable Long diaryId) {

        Diary diary = diaryService.getDiaryDetail(diaryId);
        return ApiResponse.onSuccess(DiaryConverter.toDiaryDetailDTO(diary));
    }


    @GetMapping("/mostFrequentEmotion")
    @Operation(summary = "특정 연도에 가장 많이 느낀 감정 조회", description = "사용자가 특정 연도에 작성한 일기 중 가장 많이 느낀 감정을 조회할 수 있습니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "가장 많이 느낀 감정 조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    @Parameters({
            @Parameter(name = "year", description = "조회할 일기의 연도", required = true),

    })
    public ApiResponse<DiaryResponseDTO.mostFrequentEmotionDTO> getMostFrequentEmotion(@RequestParam Integer year) {
        Map.Entry<EmotionType, Long> mostFrequentEmotion = diaryService.getMostFrequentEmotion(year);
        return ApiResponse.onSuccess(
                DiaryResponseDTO.mostFrequentEmotionDTO.builder()
                        .emotionType(mostFrequentEmotion.getKey())
                        .count(mostFrequentEmotion.getValue())
                        .build());
    }

    }
