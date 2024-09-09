package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    ResponseEntity<List<Author>> getAllAuthors();
    ResponseEntity<Author> getAuthorById(Long id);
    ResponseEntity<Author> createAuthor(Author author);
    ResponseEntity<Author> updateAuthor(Long id, Author authorDetails);
    ResponseEntity<Void> deleteAuthor(Long id);
    ResponseEntity<List<Author>> findAuthorsByName(String name);
}