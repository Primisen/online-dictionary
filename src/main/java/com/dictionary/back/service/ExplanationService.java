package com.dictionary.back.service;

import com.dictionary.back.entity.word.Explanation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExplanationService {

    Explanation create(Explanation newExplanation);

    List<Explanation> findAll();

    Optional<Explanation> findById(UUID id);

    Optional<Explanation> updateById(UUID id, Explanation explanation);

    Boolean deleteById(UUID id);
}
