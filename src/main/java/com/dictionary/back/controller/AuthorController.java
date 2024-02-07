package com.dictionary.back.controller;

import com.dictionary.back.entity.dictionary.Author;
import com.dictionary.back.exception.NotFoundException;
import com.dictionary.back.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Author newAuthor) {
        Author savedAuthor = authorService.create(newAuthor);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedAuthor.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable UUID id) {
        return authorService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable UUID id, @RequestBody Author author) {

        if (authorService.updateById(id, author).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {

        if (!authorService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
