package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE s.isLiked = 0 AND s.diary.user.username=:username")
    List<Song> findByUser(String username);
}
