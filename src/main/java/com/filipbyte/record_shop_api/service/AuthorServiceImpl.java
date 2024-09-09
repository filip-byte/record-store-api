package com.filipbyte.record_shop_api.service;

import com.filipbyte.record_shop_api.model.Author;
import com.filipbyte.record_shop_api.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Author> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(author -> new ResponseEntity<>(author, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Author> createAuthor(Author author) {
        Author savedAuthor = authorRepository.save(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Author> updateAuthor(Long id, Author authorDetails) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setName(authorDetails.getName());
                    Author updatedAuthor = authorRepository.save(author);
                    return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
