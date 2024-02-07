package com.dictionary.back.service;

import com.dictionary.back.entity.dictionary.Dictionary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DictionaryService {

    Dictionary create(Dictionary newDictionary);

    List<Dictionary> findAll();

    Optional<Dictionary> findById(UUID id);

    Optional<Dictionary> updateById(UUID id, Dictionary dictionary);

    Boolean deleteById(UUID id);
}
