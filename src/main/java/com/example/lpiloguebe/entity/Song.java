package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.enumeration.SongType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
public class Song extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long songId;

    @Column(nullable = false)
    private String name;

    @Column
    private String songURI;

    @Column(nullable = false)
    private SongType type;

    @Column(nullable = false)
    private int isLiked;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String artist;

    @JoinColumn(name = "diaryId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @Builder
    public Song(String name, String songURI, SongType type, int isLiked, Diary diary, String filePath, String artist) {
        this.name = name;
        this.songURI = songURI;
        this.type = type;
        this.isLiked = isLiked;
        this.diary = diary;
        this.filePath = filePath;
        this.artist = artist;
    }

    public void updateIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public void updateType(SongType type) {
        this.type = type;
    }
}
