package com.epam.learn.song_service.service;

import com.epam.learn.song_service.dao.SongRepository;
import com.epam.learn.song_service.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public Song getSongById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }

    public void deleteSongs(List<Integer> ids) {
        ids.forEach(id -> songRepository.deleteById(id));
    }
}