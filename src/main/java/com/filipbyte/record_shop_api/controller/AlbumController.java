package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    @Autowired
    private AlbumServiceImpl albumServiceImpl;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return albumServiceImpl.getAllAlbums();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "albums", key = "#id")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long id) {
        return albumServiceImpl.getAlbumById(id);
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return albumServiceImpl.createAlbum(album);
    }

    @PutMapping("/{id}")
    @CachePut(value = "albums", key = "#id")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album albumDetails) {
        ResponseEntity<Album> response = albumServiceImpl.updateAlbum(id, albumDetails);
        return response.getStatusCode() == HttpStatus.OK ? ResponseEntity.ok(response.getBody()) : response;
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "albums", allEntries = true)
    public ResponseEntity<Void> deleteAlbum(@PathVariable Long id) {
        return albumServiceImpl.deleteAlbum(id);
    }
}