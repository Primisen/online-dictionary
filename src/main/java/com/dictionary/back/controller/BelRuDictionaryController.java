package com.dictionary.back.controller;

import com.dictionary.back.entity.online_dictionary.BelRuDictionary;
import com.dictionary.back.exception.NotFoundException;
import com.dictionary.back.service.BelRuDictionaryService;
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
@RequestMapping("/bel-ru-dictionaries")
@RequiredArgsConstructor
public class BelRuDictionaryController {

    private final BelRuDictionaryService belRuDictionaryService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BelRuDictionary newDictionary) {
        BelRuDictionary savedDictionary = belRuDictionaryService.create(newDictionary);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedDictionary.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<BelRuDictionary> getAll() {
        return belRuDictionaryService.findAll();
    }

    @GetMapping("/{id}")
    public BelRuDictionary getById(@PathVariable UUID id) {
        return belRuDictionaryService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable UUID id, @RequestBody BelRuDictionary author) {

        if (belRuDictionaryService.updateById(id, author).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {

        if (!belRuDictionaryService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
