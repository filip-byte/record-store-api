package com.filipbyte.record_shop_api.repository;

import com.filipbyte.record_shop_api.model.Album;
import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    @Transactional
    public void testAuthorAlbumRelationship() {

        // Arrange
        Author author = new Author();
        author.setName("Test Author");
        author.setAlbums(new HashSet<>());  // Initialize the albums set using temporary HashSet<>()
        authorRepository.save(author);

        Album album = new Album(1L, "Test Album", 1969, Genre.ROCK, 1000, 10, author);
        albumRepository.save(album);

        author.getAlbums().add(album);
        authorRepository.save(author);

        // Act
        Author retrievedAuthor = authorRepository.findById(author.getId()).orElseThrow();
        Album retrievedAlbum = retrievedAuthor.getAlbums().iterator().next();

        // Assert
        assertEquals(1, retrievedAuthor.getAlbums().size());

        assertEquals("Test Album", retrievedAlbum.getTitle());
        assertEquals(1969, retrievedAlbum.getReleaseYear());
        assertEquals(Genre.ROCK, retrievedAlbum.getGenre());
        assertEquals(1000, retrievedAlbum.getPriceInPence());
        assertEquals(10, retrievedAlbum.getStock());
    }
}
