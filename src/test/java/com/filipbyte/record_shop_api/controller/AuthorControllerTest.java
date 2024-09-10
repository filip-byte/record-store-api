package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.service.AuthorServiceImpl;
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

class AuthorControllerTest {

    @Mock
    private AuthorServiceImpl authorServiceImpl;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAuthors_shouldReturnListOfAuthors() {
        // Arrange
        List<Author> authors = Arrays.asList(
                new Author(1L, "Author 1"),
                new Author(2L, "Author 2")
        );
        when(authorServiceImpl.getAllAuthors()).thenReturn(new ResponseEntity<>(authors, HttpStatus.OK));

        // Act
        ResponseEntity<List<Author>> response = authorController.getAllAuthors();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(authorServiceImpl, times(1)).getAllAuthors();
    }

    @Test
    void createAuthor_shouldReturnCreatedAuthor() {
        // Arrange
        Author newAuthor = new Author(null, "New Author");
        Author createdAuthor = new Author(1L, "New Author");
        when(authorServiceImpl.createAuthor(newAuthor)).thenReturn(new ResponseEntity<>(createdAuthor, HttpStatus.CREATED));

        // Act
        ResponseEntity<Author> response = authorController.createAuthor(newAuthor);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("New Author", response.getBody().getName());
        verify(authorServiceImpl, times(1)).createAuthor(newAuthor);
    }
}