package com.example.lpiloguebe.entity;

import com.example.lpiloguebe.enumeration.SongType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Song extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String songURI;

    @Column(nullable = false)
    private SongType type;

    @Column(nullable = false)
    private int isLiked;

    @Column(nullable = false)
    private String imagePath;

    @Column(nullable = false)
    private String artist;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diary_song> diarySongList = new ArrayList<>();

    @Builder
    public Song(String name, String songURI, SongType type, int isLiked, String imagePath, String artist) {

        this.name = name;
        this.songURI = songURI;
        this.type = type;
        this.isLiked = isLiked;
        this.imagePath = imagePath;
        this.artist = artist;
    }

    public void updateIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public void updateType(SongType type) {
        this.type = type;
    }
}
