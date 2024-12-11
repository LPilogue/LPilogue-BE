package com.example.lpiloguebe.service;

import com.example.lpiloguebe.dto.DateDTO;
import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.dto.DiaryResponseDTO;
import com.example.lpiloguebe.entity.*;
import com.example.lpiloguebe.enumeration.SongType;
import com.example.lpiloguebe.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
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

    private final CocktailDataRepository cocktailDataRepository;

    private final CocktailRepository cocktailRepository;

    private final SongRepository songRepository;


    public void createDiary(DiaryRequestDTO diaryRequestDTO) {

        User user = userRepository.findByUsername("test");
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보가 없습니다.");
        }
        log.info("유저 정보: {}", user.toString());

        // 일기 저장
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

        // 칵테일 저장
        CocktailData cocktailData = cocktailDataRepository.findByName(diaryRequestDTO.getCocktailName());
        if (cocktailData == null) {
            throw new IllegalArgumentException("칵테일 정보가 없습니다.");
        }
        log.info("칵테일 데이터 정보: {}", cocktailData.toString());

        Cocktail cocktail= Cocktail.builder()
                .cocktailData(cocktailData)
                .diary(diary)
                .build();
        log.info("칵테일 정보: {}", cocktail.toString());
        cocktailRepository.save(cocktail);
        log.info("칵테일 저장 완료");
        diary.setCocktail(cocktail);
        log.info("일기 {}의 칵테일 정보: {}", diary.getDiaryId(), diary.getCocktail().toString());

        if (diaryRequestDTO.getSongs() == null) {
            throw new IllegalArgumentException("노래 정보가 없습니다.");
        }
        // 노래 저장
        diaryRequestDTO.getSongs()
                .forEach(songRequestDTO -> {
                    Song song= Song.builder()
                            .diary(diary)
                            .artist(songRequestDTO.getArtist())
                            .filePath(songRequestDTO.getFilePath())
                            .isLiked(0)
                            .name(songRequestDTO.getName())
//                            .songURI(songRequestDTO.getSongURI())
                            .type(songRequestDTO.getType())
                            .build();
                    log.info("노래 정보: {}", song.toString());
                    diary.getSongList().add(song);
                    log.info("일기 {}의 노래 리스트: {}", diary.getDiaryId(), diary.getSongList());
                    songRepository.save(song);
                    log.info("노래 저장 완료");
                });


    }

    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("일기 정보가 없습니다."));
        log.info("삭제할 일기 정보: {}", diary.toString());
        diaryRepository.delete(diary);
        log.info("일기 삭제 완료");
    }

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
            Optional<Song> mainSong = diary.getSongList().stream()
                    .filter(song -> song.getType() == SongType.MAIN)
                    .findAny();

            // DiaryResponseDTO 생성
            DiaryResponseDTO diaryResponseDTO = DiaryResponseDTO.builder()
                    .createdAt(diary.getCreatedAt())
                    .content(diary.getContent())
                    .songName(mainSong.map(Song::getName).orElse(null))
                    .songURI(mainSong.map(Song::getSongURI).orElse(null))
                    .artist(mainSong.map(Song::getArtist).orElse(null))
                    .songFilePath(mainSong.map(Song::getFilePath).orElse(null))
                    .cocktailName(diary.getCocktail().getCocktailData().getName())
                    .cocktailFilePath(diary.getCocktail().getCocktailData().getFilePath())
                    .build();

            log.info("일기 응답 정보: {}", diaryResponseDTO.toString());
            diaryResponseDTOList.add(diaryResponseDTO);
        });

        return diaryResponseDTOList;
    }

}
