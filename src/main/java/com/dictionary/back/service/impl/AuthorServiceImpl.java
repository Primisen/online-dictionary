package com.dictionary.back.service.impl;

import com.dictionary.back.entity.dictionary.Author;
import com.dictionary.back.repository.AuthorRepository;
import com.dictionary.back.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author create(Author newAuthor) {
        return authorRepository.save(newAuthor);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(UUID id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> updateById(UUID id, Author author) {

        AtomicReference<Optional<Author>> atomicReference = new AtomicReference<>();

        authorRepository.findById(id).ifPresentOrElse(foundAuthor -> {
            foundAuthor.setName(author.getName());
            foundAuthor.setSurname(author.getSurname());
            atomicReference.set(Optional.of(
                    authorRepository.save(foundAuthor)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
