package com.dictionary.back.service.impl;

import com.dictionary.back.entity.word.Word;
import com.dictionary.back.repository.WordRepository;
import com.dictionary.back.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public Word create(Word newWord) {
        return wordRepository.save(newWord);
    }

    @Override
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    public Optional<Word> findById(UUID id) {
        return wordRepository.findById(id);
    }

    @Override
    public Optional<Word> updateById(UUID id, Word word) {

        AtomicReference<Optional<Word>> atomicReference = new AtomicReference<>();

        wordRepository.findById(id).ifPresentOrElse(foundWord -> {
            foundWord.setCategory(word.getCategory());
            foundWord.setText(word.getText());
            foundWord.setLanguage(word.getLanguage());
            atomicReference.set(Optional.of(
                    wordRepository.save(foundWord)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
