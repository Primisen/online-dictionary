package com.dictionary.back.service.impl;

import com.dictionary.back.entity.online_dictionary.BelRuDictionary;
import com.dictionary.back.entity.word.Category;
import com.dictionary.back.repository.BelRuDictionaryRepository;
import com.dictionary.back.service.BelRuDictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class BelRuDictionaryServiceImpl implements BelRuDictionaryService {

    private final BelRuDictionaryRepository belRuDictionaryRepository;

    @Override
    public BelRuDictionary create(BelRuDictionary newDictionary) {
        return belRuDictionaryRepository.save(newDictionary);
    }

    @Override
    public List<BelRuDictionary> findAll() {
        return belRuDictionaryRepository.findAll();
    }

    @Override
    public Optional<BelRuDictionary> findById(UUID id) {
        return belRuDictionaryRepository.findById(id);
    }

    @Override
    public Optional<BelRuDictionary> updateById(UUID id, BelRuDictionary dictionary) {
        AtomicReference<Optional<BelRuDictionary>> atomicReference = new AtomicReference<>();

        belRuDictionaryRepository.findById(id).ifPresentOrElse(foundDictionary -> {
            foundDictionary.setBel(dictionary.getBel());
            foundDictionary.setRu(dictionary.getRu());
            atomicReference.set(Optional.of(
                    belRuDictionaryRepository.save(foundDictionary)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (belRuDictionaryRepository.existsById(id)) {
            belRuDictionaryRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
