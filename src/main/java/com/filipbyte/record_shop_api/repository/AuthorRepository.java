package com.filipbyte.record_shop_api.repository;

import com.filipbyte.record_shop_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String name);
}

