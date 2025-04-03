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

    // 외래키를 가지는 쪽이 JoinColumn
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "diary_song_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary_song> diary_songs = new ArrayList<>();

    @OneToOne(mappedBy = "diary_cocktail_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diary_cocktail diary_cocktail;

    @Builder
    public Diary(String content, LocalDateTime createdAt, User user) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;

    }
}
