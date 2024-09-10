package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.model.Genre;
import com.filipbyte.record_shop_api.service.AlbumServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlbumController.class)
public class AlbumControllerTest_WithCache {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumServiceImpl albumServiceImpl;

    @Test
    public void testGetAllAlbums() throws Exception {
        // Given
        Author author = new Author(1L, "Test Author");
        List<Album> albums = Arrays.asList(
                new Album(1L, "Test Album 1", 2000, Genre.ROCK, 1500, 10, author),
                new Album(2L, "Test Album 2", 2001, Genre.POP, 1200, 15, author)
        );

        // When
        Mockito.when(albumServiceImpl.getAllAlbums())
                .thenReturn(ResponseEntity.ok(albums));

        // Then
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Test Album 1"))
                .andExpect(jsonPath("$[1].title").value("Test Album 2"));
    }

    @Test
    public void testGetAlbumById() throws Exception {
        // Given
        Author author = new Author(1L, "Test Author");
        Album album = new Album(1L, "Test Album", 2000, Genre.ROCK, 1500, 10, author);

        // When
        Mockito.when(albumServiceImpl.getAlbumById(1L))
                .thenReturn(ResponseEntity.ok(album));

        // Then
        mockMvc.perform(get("/api/albums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Album"))
                .andExpect(jsonPath("$.genre").value("ROCK"));
    }

    @Test
    public void testCreateAlbum() throws Exception {
        // Given
        Author author = new Author(1L, "Test Author");
        Album album = new Album(1L, "Test Album", 2000, Genre.ROCK, 1500, 10, author);

        // When
        Mockito.when(albumServiceImpl.createAlbum(any(Album.class)))
                .thenReturn(ResponseEntity.ok(album));

        // Then
        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Test Album\", \"releaseYear\": 2000, \"genre\": \"ROCK\", \"priceInPence\": 1500, \"stock\": 10, \"author\": {\"id\": 1, \"name\": \"Test Author\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Album"));
    }

    @Test
    public void testUpdateAlbum() throws Exception {
        // Given
        Author author = new Author(1L, "Test Author");
        Album updatedAlbum = new Album(1L, "Updated Album", 2000, Genre.ROCK, 1800, 5, author);

        // When
        Mockito.when(albumServiceImpl.updateAlbum(eq(1L), any(Album.class)))
                .thenReturn(ResponseEntity.ok(updatedAlbum));

        // Then
        mockMvc.perform(put("/api/albums/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Updated Album\", \"releaseYear\": 2000, \"genre\": \"ROCK\", \"priceInPence\": 1800, \"stock\": 5, \"author\": {\"id\": 1, \"name\": \"Test Author\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Album"))
                .andExpect(jsonPath("$.priceInPence").value(1800));
    }

    @Test
    public void testDeleteAlbum() throws Exception {
        // When
        Mockito.when(albumServiceImpl.deleteAlbum(1L))
                .thenReturn(ResponseEntity.noContent().build());

        // Then
        mockMvc.perform(delete("/api/albums/1"))
                .andExpect(status().isNoContent());
    }
}
