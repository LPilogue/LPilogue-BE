package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.enumeration.SongType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Song extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String songURI;

    @Column(nullable = false)
    private SongType type;

    @Column(nullable = false)
    private int isLiked;

    @JoinColumn(name = "diaryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public Song(String name, String songURI, SongType type, int isLiked, Diary diary) {
        this.name = name;
        this.songURI = songURI;
        this.type = type;
        this.isLiked = isLiked;
        this.diary = diary;
    }
}
