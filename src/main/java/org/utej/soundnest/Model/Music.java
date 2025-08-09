package org.utej.soundnest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "musics")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;
    private String musicName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id", nullable = false)
    private Album album;
    @Lob
    @Column(columnDefinition = "BYTEA")
    private byte[] musicTrack;
    private Long views;

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public byte[] getMusicTrack() {
        return musicTrack;
    }

    public void setMusicTrack(byte[] musicTrack) {
        this.musicTrack = musicTrack;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
