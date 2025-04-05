package com.example.lpiloguebe.repository;

import com.example.lpiloguebe.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = """
    select s.* from song as s
    join diary_song as ds on s.id = ds.song_id
    join diary as d on ds.diary_id = d.id
    join user as u on d.user_id = u.id
    where u.username = :username
    and s.isLiked = 0
""", nativeQuery = true)
    List<Song> findUnlikedSongListByUsername(@Param("username") String username);

}
