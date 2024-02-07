package com.dictionary.back.service;

import com.dictionary.back.entity.word.Word;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WordService {

    Word create(Word newWord);

    List<Word> findAll();

    Optional<Word> findById(UUID id);

    Optional<Word> updateById(UUID id, Word word);

    Boolean deleteById(UUID id);
}
