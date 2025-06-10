package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Diary;
import com.example.lpiloguebe.entity.Diary_song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Diary_songRepository extends JpaRepository<Diary_song, Long> {

    Optional<List<Diary_song>> findAllByDiary(Diary diary);
}
