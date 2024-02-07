package com.dictionary.back.service.impl;

import com.dictionary.back.entity.word.Explanation;
import com.dictionary.back.repository.ExplanationRepository;
import com.dictionary.back.service.ExplanationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ExplanationServiceImpl implements ExplanationService {

    private final ExplanationRepository explanationRepository;

    @Override
    public Explanation create(Explanation newExplanation) {
        return explanationRepository.save(newExplanation);
    }

    @Override
    public List<Explanation> findAll() {
        return explanationRepository.findAll();
    }

    @Override
    public Optional<Explanation> findById(UUID id) {
        return explanationRepository.findById(id);
    }

    @Override
    public Optional<Explanation> updateById(UUID id, Explanation explanation) {

        AtomicReference<Optional<Explanation>> atomicReference = new AtomicReference<>();

        explanationRepository.findById(id).ifPresentOrElse(foundExplanation -> {
            foundExplanation.setDictionary(explanation.getDictionary());
            foundExplanation.setWord(explanation.getWord());
            foundExplanation.setText(explanation.getText());
            atomicReference.set(Optional.of(
                    explanationRepository.save(foundExplanation)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (explanationRepository.existsById(id)) {
            explanationRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
