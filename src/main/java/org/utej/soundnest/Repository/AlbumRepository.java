package org.utej.soundnest.Repository;

import org.utej.soundnest.Model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByAlbumName(String albumName);
    Album findByAlbumId(Long albumId);
}