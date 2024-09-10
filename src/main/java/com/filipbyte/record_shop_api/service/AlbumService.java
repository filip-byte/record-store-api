package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlbumService {

    ResponseEntity<List<Album>> getAllAlbums();
    ResponseEntity<Album> getAlbumById(Long id);
    ResponseEntity<Album> createAlbum(Album album);
    ResponseEntity<Album> updateAlbum(Long id, Album albumDetails);
    ResponseEntity<Void> deleteAlbum(Long id);

    // todo
    ResponseEntity<List<Album>> findAlbumsByAuthorId(Long authorId);
    ResponseEntity<List<Album>> findAlbumsByGenre(Genre genre);
    ResponseEntity<List<Album>> findAlbumsByReleaseYear(Integer year);
    ResponseEntity<List<Album>> findAlbumsInStock();

}
