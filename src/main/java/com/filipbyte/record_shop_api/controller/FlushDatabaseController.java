package com.filipbyte.record_shop_api.controller;

import com.filipbyte.record_shop_api.repository.AlbumRepository;
import com.filipbyte.record_shop_api.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deleteAllRecords")
public class FlushDatabaseController {

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AuthorRepository authorRepository;

    @DeleteMapping
    ResponseEntity<String> deleteAllRecords() {
        albumRepository.deleteAll();
        authorRepository.deleteAll();
        return new ResponseEntity<>("All records deleted", HttpStatus.OK);

    }
}
