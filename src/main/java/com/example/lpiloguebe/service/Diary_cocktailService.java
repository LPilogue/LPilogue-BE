package com.example.lpiloguebe.service;

import com.example.lpiloguebe.apiPayload.code.status.ErrorStatus;
import com.example.lpiloguebe.dto.DiaryRequestDTO;
import com.example.lpiloguebe.entity.Cocktail;
import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.entity.Diary_cocktail;
import com.example.lpiloguebe.exception.GeneralException;
import com.example.lpiloguebe.repository.CocktailRepository;
import com.example.lpiloguebe.repository.DiaryRepository;
import com.example.lpiloguebe.repository.Diary_cocktailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Diary_cocktailService {

    private final CocktailRepository cocktailRepository;

    private final Diary_cocktailRepository diary_cocktailRepository;
    private final DiaryRepository diaryRepository;

    /**
     * 일기와 칵테일 정보를 연결하는 메서드
     * @param diary
     * @param diaryRequestDTO
     * @return Diary
     */
    public Diary addCocktailToDiary(Diary diary, DiaryRequestDTO diaryRequestDTO) {

        // 칵테일 정보가 없으면 예외 처리
        Cocktail cocktail = cocktailRepository.findByName(diaryRequestDTO.getCocktailName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.COCKTAIL_NOT_FOUND));

        log.info("칵테일 정보: {}", cocktail.toString());
        /*
        DTO에서 칵테일 정보 가져와서 Diary_cocktail 테이블에 일기와 칵테일 정보 연결
        Diary 테이블에 Diary_cocktail 리스트 추가
         */
        Diary_cocktail diary_cocktail= Diary_cocktail.builder()
                .cocktail(cocktail)
                .diary(diary)
                .build();
        log.info("Diary_cocktail 정보: {}", diary_cocktail.toString());
        diary_cocktailRepository.save(diary_cocktail);
        diary.addDiary_cocktail(diary_cocktail);

        log.info("Diary_cocktail 저장, diary {}에 추가 완료", diary.getId());
        return diaryRepository.save(diary);
    }
}
