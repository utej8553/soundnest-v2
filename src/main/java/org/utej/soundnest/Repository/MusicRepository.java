package org.utej.soundnest.Repository;

import java.util.*;

import org.utej.soundnest.Model.Album;
import org.utej.soundnest.Model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Music findByMusicId(Long musicId);
    Music findByMusicName(String musicName);
    List<Music> findByAlbum(Album album);
    List<Music> findByMusicNameContainingIgnoreCase(String keyword);
}