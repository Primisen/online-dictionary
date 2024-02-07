package com.dictionary.back.controller;

import com.dictionary.back.entity.dictionary.Dictionary;
import com.dictionary.back.exception.NotFoundException;
import com.dictionary.back.service.DictionaryService;
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
@RequestMapping("/dictionaries")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Dictionary newDictionary) {
        Dictionary savedDictionary = dictionaryService.create(newDictionary);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedDictionary.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Dictionary> getAll() {
        return dictionaryService.findAll();
    }

    @GetMapping("/{id}")
    public Dictionary getById(@PathVariable UUID id) {
        return dictionaryService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable UUID id, @RequestBody Dictionary dictionary) {

        if (dictionaryService.updateById(id, dictionary).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {

        if (!dictionaryService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
