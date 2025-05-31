package com.example.lpiloguebe.service;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.*;
import com.example.lpiloguebe.enumeration.EmotionType;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.exception.GeneralException;
import com.example.lpiloguebe.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final DiaryRepository diaryRepository;

    private final UserRepository userRepository;

    /**
     * 일기 생성
     * @param diaryRequestDTO
     * @return diary
     */
    public Diary createDiary(DiaryRequestDTO diaryRequestDTO) {

        // SecurityContextHolder에서 인증된 사용자 정보 가져오기
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        // 작성 날짜가 3일 이상 전인지 확인
        boolean isValidate = validateCreatedAt(diaryRequestDTO.getCreatedAt());
        if(isValidate) {
            throw new GeneralException(ErrorStatus.INVALID_Date);
        }

        Diary diary=Diary.builder()
                .content(diaryRequestDTO.getContent())
                .user(user)
                .createdAt(diaryRequestDTO.getCreatedAt())
                .emotionType(diaryRequestDTO.getEmotionType())
                .build();
        log.info("일기 정보: {}", diary.toString());
        diaryRepository.save(diary);
        log.info("일기 저장 완료");
        user.getDiaryList().add(diary);
        log.info("user {}의 일기 리스트: {}", user.getUsername(), user.getDiaryList());

        return diary;
    }

    public boolean validateCreatedAt(LocalDateTime createdAt) {
        LocalDateTime threeDaysAgo = LocalDateTime.now().minusDays(3);

        return createdAt.isBefore(threeDaysAgo);
    }


    /**
     * 일기 삭제
     * @param diaryId
     */
    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DIARY_NOT_FOUND));
        log.info("삭제할 일기 정보: {}", diary.toString());
        diaryRepository.delete(diary);
        log.info("일기 삭제 완료");
    }

    /**
     * 일기 조회
     * @param year, month
     * @return List<DiaryResponseDTO>
     */

    public List<Diary> getDiaryPreviewList(int year, int month) {
        // 년도, 월로 일기 리스트 조회
        LocalDateTime startDate = LocalDate.of(year, month, 1)
                .atStartOfDay();
        LocalDateTime endDate = LocalDate.of(year, month, 1)
                .withDayOfMonth(LocalDate.of(year, month, 1)    // 해당 월의 마지막 날 몇 일인지 계산
                        .lengthOfMonth()).atTime(23, 59, 59, 999999999); // 마지막 날 23:59:59.999999999

        List<Diary> diaryList = diaryRepository.findDiariesByDateRange(startDate, endDate);

        log.info("일기 리스트: {}", diaryList);



        return diaryList;
    }

    public Diary getDiaryDetail(Long diaryId) {

        return diaryRepository.findById(diaryId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.DIARY_NOT_FOUND));
    }

    public Map.Entry<EmotionType, Long> getMostFrequentEmotionYearly(Integer year) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return user.getDiaryList().stream()
                .filter(diary -> diary.getCreatedAt().getYear() == year)
                .map(Diary::getEmotionType)
                .collect(Collectors.groupingBy(
                        emotion -> emotion,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new GeneralException(ErrorStatus.NO_DIARY_FOR_YEAR));

    }

    public Map.Entry<EmotionType, Long> getMostFrequentEmotionMonthly(Integer year, Integer month) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return user.getDiaryList().stream()
                .filter(diary -> diary.getCreatedAt().getYear() == year && diary.getCreatedAt().getMonthValue() == month)
                .map(Diary::getEmotionType)
                .collect(Collectors.groupingBy(
                        emotion -> emotion,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(() -> new GeneralException(ErrorStatus.NO_DIARY_FOR_MONTH));
    }
}
