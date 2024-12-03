package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cocktail extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diaryId")
    private Diary diary;

    @Builder
    public Cocktail(String name, String color, String ingredient, String description, byte[] image, Diary diary) {
        this.name = name;
        this.color = color;
        this.ingredient = ingredient;
        this.description = description;
        this.image = image;
        this.diary = diary;
    }
}
