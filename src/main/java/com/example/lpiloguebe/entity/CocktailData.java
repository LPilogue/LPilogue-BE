package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

    @OneToMany(mappedBy = "cocktailData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cocktail> cocktailList=new ArrayList<>();

    @Builder
    public CocktailData(String name, String color, String ingredients, String description, String filePath) {
        this.name = name;
        this.color = color;
        this.ingredients = ingredients;
        this.description = description;
        this.filePath = filePath;

    }
}
