package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.service.AlbumService;
import com.filipbyte.record_shop_api.service.AlbumServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlbumControllerTest {

    @Mock
    private AlbumServiceImpl albumServiceImpl;

    @InjectMocks
    private AlbumController albumController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAlbums_shouldReturnListOfAlbums() {
        // Arrange
        List<Author> authors = Arrays.asList(
                new Author(1L, "Author 1"),
                new Author(2L, "Author 2")
        );
        List<Album> albums = Arrays.asList(
                new Album(1L, "Album 1", 2021, null, 1000, 10, authors.getFirst()),
                new Album(2L, "Album 2", 2022, null, 1500, 5, authors.getLast())
        );
        when(albumServiceImpl.getAllAlbums()).thenReturn(new ResponseEntity<>(albums, HttpStatus.OK));

        // Act
        ResponseEntity<List<Album>> response = albumController.getAllAlbums();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(albumServiceImpl, times(1)).getAllAlbums();
    }

    @Test
    void getAlbumById_shouldReturnAlbum_whenAlbumExists() {
        // Arrange
        Long albumId = 1L;
        Album album = new Album(albumId, "Test Album", 2023, null, 2000, 15, null);
        when(albumServiceImpl.getAlbumById(albumId)).thenReturn(new ResponseEntity<>(album, HttpStatus.OK));

        // Act
        ResponseEntity<Album> response = albumController.getAlbumById(albumId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(albumId, response.getBody().getId());
        assertEquals("Test Album", response.getBody().getTitle());
        verify(albumServiceImpl, times(1)).getAlbumById(albumId);
    }
}