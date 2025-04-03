package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Diary_cocktail extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "diary_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @JoinColumn(name = "cocktail_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cocktail cocktail;

    @Builder
    public Diary_cocktail(Diary diary, Cocktail cocktail) {
        this.diary = diary;
        this.cocktail = cocktail;
    }

}
