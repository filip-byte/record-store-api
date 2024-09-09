package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Genre;

import java.util.List;
import java.util.Optional;

public interface AlbumService {

    List<Album> getAllAlbums();
    Optional<Album> getAlbumById(Long id);
    Album createAlbum(Album album);
    Album updateAlbum(Long id, Album albumDetails);
    void deleteAlbum(Long id);
    List<Album> findAlbumsByAuthorId(Long authorId);
    List<Album> findAlbumsByGenre(Genre genre);
    List<Album> findAlbumsByReleaseYear(Integer year);
    List<Album> findAlbumsInStock();

}
