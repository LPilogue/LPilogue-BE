package com.example.lpiloguebe.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary_song extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="diary_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Diary diary;

    @JoinColumn(name="song_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Song song;

    @Builder
    public Diary_song(Diary diary, Song song) {
        this.diary = diary;
        this.song = song;
    }
}
