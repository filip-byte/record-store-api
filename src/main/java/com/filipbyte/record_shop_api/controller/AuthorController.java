package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.service.AuthorServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    // todo: we need authorService implementation here
    private AuthorServiceImpl authorServiceImpl;

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return authorServiceImpl.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return authorServiceImpl.getAuthorById(id);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return authorServiceImpl.createAuthor(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        return authorServiceImpl.updateAuthor(id, authorDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        return authorServiceImpl.deleteAuthor(id);
    }

}