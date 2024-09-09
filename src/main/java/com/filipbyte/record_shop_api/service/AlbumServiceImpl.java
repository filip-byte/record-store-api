package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Genre;
import com.filipbyte.record_shop_api.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public ResponseEntity<List<Album>> getAllAlbums() {
        List<Album> albums = (List<Album>) albumRepository.findAll();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Album> getAlbumById(Long id) {
        return albumRepository.findById(id)
                .map(album -> new ResponseEntity<>(album, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Album> createAlbum(Album album) {
        Album savedAlbum = albumRepository.save(album);
        return new ResponseEntity<>(savedAlbum, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Album> updateAlbum(Long id, Album albumDetails) {
        return albumRepository.findById(id)
                .map(album -> {
                    album.setTitle(albumDetails.getTitle());
                    album.setReleaseYear(albumDetails.getReleaseYear());
                    album.setGenre(albumDetails.getGenre());
                    album.setPriceInPence(albumDetails.getPriceInPence());
                    album.setStock(albumDetails.getStock());
                    album.setAuthor(albumDetails.getAuthor());
                    Album updatedAlbum = albumRepository.save(album);
                    return new ResponseEntity<>(updatedAlbum, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteAlbum(Long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Album>> findAlbumsByAuthorId(Long authorId) {
        // todo: write logic for below method:
        // List<Album> albums = albumRepository.findByAuthorId(authorId);

        List<Album> albums = new ArrayList<>();

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Album>> findAlbumsByGenre(Genre genre) {
        // todo: write logic for below method:
        // List<Album> albums = albumRepository.findByGenre(genre);

        List<Album> albums = new ArrayList<>();

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Album>> findAlbumsByReleaseYear(Integer year) {
        // todo: write logic for below method:
        // List<Album> albums = albumRepository.findByReleaseYear(year);

        List<Album> albums = new ArrayList<>();

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Album>> findAlbumsInStock() {
        // todo: write logic for below method:
        // List<Album> albums = albumRepository.findByStockGreaterThan(0);

        List<Album> albums = new ArrayList<>();

        return new ResponseEntity<>(albums, HttpStatus.OK);
    }
}
