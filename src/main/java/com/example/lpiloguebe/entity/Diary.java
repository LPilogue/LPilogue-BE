package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 외래키를 가지는 쪽이 연관관계의 주인, JoinColumn 가짐
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary_song> diarySongList = new ArrayList<>();

    @OneToOne(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diary_cocktail diary_cocktail;

    @Builder
    public Diary(String content, LocalDateTime createdAt, User user) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }

    public void addDiary_cocktail(Diary_cocktail diary_cocktail) {
        this.diary_cocktail = diary_cocktail;
    }

}
