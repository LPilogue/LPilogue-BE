package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cocktail extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailId;

    @JoinColumn(name = "diaryId")
    @OneToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @JoinColumn(name = "cocktailDataId")
    @ManyToOne(fetch = FetchType.LAZY)
    private CocktailData cocktailData;

    @Builder
    public Cocktail(Diary diary, CocktailData cocktailData) {
        this.diary = diary;
        this.cocktailData = cocktailData;

    }


}
