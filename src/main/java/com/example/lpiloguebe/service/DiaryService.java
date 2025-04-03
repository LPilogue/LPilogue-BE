package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.DateDTO;
import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.*;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        // 로그인 구현 끝나면 수정
        User user = userRepository.findByUsername("test");
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        log.info("유저 정보: {}", user.toString());

        Diary diary=Diary.builder()
                .content(diaryRequestDTO.getContent())
                .user(user)
                .createdAt(diaryRequestDTO.getCreatedAt())
                .build();
        log.info("일기 정보: {}", diary.toString());
        diaryRepository.save(diary);
        log.info("일기 저장 완료");
        user.getDiaryList().add(diary);
        log.info("user {}의 일기 리스트: {}", user.getUsername(), user.getDiaryList());

        return diary;
    }

    /**
     * 일기 삭제
     * @param diaryId
     */
    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("일기 정보가 없습니다."));
        log.info("삭제할 일기 정보: {}", diary.toString());
        diaryRepository.delete(diary);
        log.info("일기 삭제 완료");
    }

    /**
     * 일기 조회
     * @param dateDTO
     * @return List<DiaryResponseDTO>
     */

    public List<DiaryResponseDTO> getDiaryList(DateDTO dateDTO) {
        // 년도, 월로 일기 리스트 조회
        LocalDateTime startDate = LocalDate.of(dateDTO.getYear(), dateDTO.getMonth(), 1)
                .atStartOfDay();
        LocalDateTime endDate = LocalDate.of(dateDTO.getYear(), dateDTO.getMonth(), 1)
                .withDayOfMonth(LocalDate.of(dateDTO.getYear(), dateDTO.getMonth(), 1)
                        .lengthOfMonth()).atTime(23, 59, 59, 999999999); // 마지막 날 23:59:59.999999999

        List<Diary> diaryList = diaryRepository.findDiariesByDateRange(startDate, endDate);

        log.info("일기 리스트: {}", diaryList);
        List<DiaryResponseDTO> diaryResponseDTOList = new ArrayList<>();

        diaryList.forEach(diary -> {
            // MAIN 타입의 Song을 가져옴
            Optional<Diary_song> mainSong = diary.getDiarySongList().stream()
                    .filter(diary_song -> diary_song.getSong().getType() == SongType.MAIN)
                    .findAny();

            // DiaryResponseDTO 생성
            DiaryResponseDTO diaryResponseDTO = DiaryResponseDTO.builder()
                    .createdAt(diary.getCreatedAt())
                    .content(diary.getContent())
                    // map을 사용하여 Optional에서 값을 가져옴
                    .songName(mainSong.map(diary_song -> diary_song.getSong().getName()).orElse(null))
                    .artist(mainSong.map(diary_song -> diary_song.getSong().getArtist()).orElse(null))
                    .songURI(mainSong.map(diary_song -> diary_song.getSong().getSongURI()).orElse(null))
                    .songImagePath(mainSong.map(diary_song -> diary_song.getSong().getImagePath()).orElse(null))
                    .cocktailName(diary.getDiary_cocktail().getCocktail().getName())
                    .cocktailImagePath(diary.getDiary_cocktail().getCocktail().getImagePath())
                    .build();

            log.info("일기 응답 정보: {}", diaryResponseDTO.toString());
            diaryResponseDTOList.add(diaryResponseDTO);
        });

        return diaryResponseDTOList;
    }

}
