package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Diary_song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Diary_songRepository extends JpaRepository<Diary_song, Long> {

}
