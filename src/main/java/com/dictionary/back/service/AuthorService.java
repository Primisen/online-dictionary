package com.dictionary.back.service;

import com.dictionary.back.entity.paper_dictionary.Author;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {

    Author create(Author newAuthor);

    List<Author> findAll();

    Optional<Author> findById(UUID id);

    Optional<Author> updateById(UUID id, Author author);

    Boolean deleteById(UUID id);
}
