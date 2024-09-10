package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.model.Genre;
import com.filipbyte.record_shop_api.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    private Album testAlbum;
    private Author testAuthor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testAuthor = new Author(1L, "Test Author");
        testAlbum = new Album(1L, "Test Album", 2023, Genre.ROCK, 1000, 10, testAuthor);
    }

    @Test
    void getAllAlbums_shouldReturnAllAlbums() {
        when(albumRepository.findAll()).thenReturn(Arrays.asList(testAlbum));

        ResponseEntity<List<Album>> response = albumService.getAllAlbums();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(testAlbum);
    }

    @Test
    void getAlbumById_whenAlbumExists_shouldReturnAlbum() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(testAlbum));

        ResponseEntity<Album> response = albumService.getAlbumById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(testAlbum);
    }

    @Test
    void getAlbumById_whenAlbumDoesNotExist_shouldReturnNotFound() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Album> response = albumService.getAlbumById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void createAlbum_shouldCreateAndReturnAlbum() {
        when(albumRepository.save(any(Album.class))).thenReturn(testAlbum);

        ResponseEntity<Album> response = albumService.createAlbum(testAlbum);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(testAlbum);
    }

    @Test
    void updateAlbum_whenAlbumExists_shouldUpdateAndReturnAlbum() {
        Album updatedAlbum = new Album(1L, "Updated Album", 2024, Genre.POP, 1500, 15, testAuthor);
        when(albumRepository.findById(1L)).thenReturn(Optional.of(testAlbum));
        when(albumRepository.save(any(Album.class))).thenReturn(updatedAlbum);

        ResponseEntity<Album> response = albumService.updateAlbum(1L, updatedAlbum);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(updatedAlbum);
    }

    @Test
    void updateAlbum_whenAlbumDoesNotExist_shouldReturnNotFound() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Album> response = albumService.updateAlbum(1L, testAlbum);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void deleteAlbum_whenAlbumExists_shouldDeleteAndReturnNoContent() {
        when(albumRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = albumService.deleteAlbum(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(albumRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAlbum_whenAlbumDoesNotExist_shouldReturnNotFound() {
        when(albumRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = albumService.deleteAlbum(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(albumRepository, never()).deleteById(anyLong());
    }
}