package com.filipbyte.record_shop_api.repository;

import com.filipbyte.record_shop_api.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {}

