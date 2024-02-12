package com.dictionary.back.service;


import com.dictionary.back.entity.online_dictionary.BelRuDictionary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BelRuDictionaryService {

    BelRuDictionary create(BelRuDictionary newDictionary);

    List<BelRuDictionary> findAll();

    Optional<BelRuDictionary> findById(UUID id);

    Optional<BelRuDictionary> updateById(UUID id, BelRuDictionary dictionary);

    Boolean deleteById(UUID id);
}
