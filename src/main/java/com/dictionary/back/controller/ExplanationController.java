package com.dictionary.back.controller;

import com.dictionary.back.entity.word.Explanation;
import com.dictionary.back.exception.NotFoundException;
import com.dictionary.back.service.ExplanationService;
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
@RequestMapping("/explanations")
@RequiredArgsConstructor
public class ExplanationController {

    private final ExplanationService explanationService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Explanation newExplanation) {
        Explanation savedExplanation = explanationService.create(newExplanation);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", savedExplanation.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Explanation> getAll() {
        return explanationService.findAll();
    }

    @GetMapping("/{id}")
    public Explanation getById(@PathVariable UUID id) {
        return explanationService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable UUID id, @RequestBody Explanation explanation) {

        if (explanationService.updateById(id, explanation).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable UUID id) {

        if (!explanationService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
