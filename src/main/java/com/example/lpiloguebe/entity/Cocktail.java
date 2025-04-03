package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Cocktail extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String description;

    @Column
    private String imagePath;

    @OneToMany(mappedBy = "cocktail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary_cocktail> diaryCocktailList=new ArrayList<>();

    @Builder
    public Cocktail(String name, String color, String ingredients, String description, String imagePath) {
        this.name = name;
        this.color = color;
        this.ingredients = ingredients;
        this.description = description;
        this.imagePath = imagePath;

    }
}
