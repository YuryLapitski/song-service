package com.epam.learn.song_service.controller;

import com.epam.learn.song_service.model.Song;
import com.epam.learn.song_service.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping
    public ResponseEntity<Object> createSong(@RequestBody Song song) {
        Song createdSong = songService.createSong(song);
        return ResponseEntity.ok().body(Map.of("id", createdSong.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Integer id) {
        Song song = songService.getSongById(id);
        return (song != null ? ResponseEntity.ok(song) : ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteSongs(@RequestParam String ids) {
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        songService.deleteSongs(idList);
        return ResponseEntity.ok().body(Map.of("ids", idList));
    }
}
