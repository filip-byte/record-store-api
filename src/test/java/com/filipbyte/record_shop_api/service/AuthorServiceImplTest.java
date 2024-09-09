package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.repository.AuthorRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

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
        when(authorRepository.findAll()).thenReturn(authors);

        // Act
        ResponseEntity<List<Author>> response = authorService.getAllAuthors();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("Author 1", response.getBody().get(0).getName());
        assertEquals("Author 2", response.getBody().get(1).getName());
    }

    @Test
    void getAuthorById_existingId_shouldReturnAuthor() {
        // Arrange
        Author author = new Author(1L, "Test Author");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        // Act
        ResponseEntity<Author> response = authorService.getAuthorById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Author", response.getBody().getName());
    }

    @Test
    void getAuthorById_nonExistingId_shouldReturnNotFound() {
        // Arrange
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Author> response = authorService.getAuthorById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void createAuthor_shouldReturnCreatedAuthor() {
        // Arrange
        Author authorToCreate = new Author(null, "New Author");
        Author createdAuthor = new Author(1L, "New Author");
        when(authorRepository.save(authorToCreate)).thenReturn(createdAuthor);

        // Act
        ResponseEntity<Author> response = authorService.createAuthor(authorToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("New Author", response.getBody().getName());
    }

    @Test
    void updateAuthor_existingId_shouldReturnUpdatedAuthor() {
        // Arrange
        Author existingAuthor = new Author(1L, "Old Name");
        Author updatedAuthor = new Author(1L, "Updated Name");
        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(updatedAuthor);

        // Act
        ResponseEntity<Author> response = authorService.updateAuthor(1L, updatedAuthor);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Name", response.getBody().getName());
    }

    @Test
    void updateAuthor_nonExistingId_shouldReturnNotFound() {
        // Arrange
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Author> response = authorService.updateAuthor(1L, new Author(1L, "Updated Name"));

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void deleteAuthor_existingId_shouldReturnNoContent() {
        // Arrange
        when(authorRepository.existsById(1L)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = authorService.deleteAuthor(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(authorRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteAuthor_nonExistingId_shouldReturnNotFound() {
        // Arrange
        when(authorRepository.existsById(1L)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = authorService.deleteAuthor(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(authorRepository, never()).deleteById(anyLong());
    }

}