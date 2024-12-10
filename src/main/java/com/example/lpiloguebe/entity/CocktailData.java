package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CocktailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cocktailDataId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String ingredients;

    @Column(nullable = false)
    private String description;

    @Column
    private String filePath;

    @OneToOne(mappedBy = "cocktailData", cascade = CascadeType.ALL, orphanRemoval = true)
    private Cocktail cocktail;

    @Builder
    public CocktailData(String name, String color, String ingredients, String description, String filePath) {
        this.name = name;
        this.color = color;
        this.ingredients = ingredients;
        this.description = description;
        this.filePath = filePath;

    }
}
