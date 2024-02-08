package com.dictionary.back.service.impl;

import com.dictionary.back.entity.paper_dictionary.Dictionary;
import com.dictionary.back.repository.DictionaryRepository;
import com.dictionary.back.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryRepository dictionaryRepository;

    @Override
    public Dictionary create(Dictionary newDictionary) {
        return dictionaryRepository.save(newDictionary);
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryRepository.findAll();
    }

    @Override
    public Optional<Dictionary> findById(UUID id) {
        return dictionaryRepository.findById(id);
    }

    @Override
    public Optional<Dictionary> updateById(UUID id, Dictionary dictionary) {

        AtomicReference<Optional<Dictionary>> atomicReference = new AtomicReference<>();

        dictionaryRepository.findById(id).ifPresentOrElse(foundDictionary -> {
            foundDictionary.setName(dictionary.getName());
            foundDictionary.setYear(dictionary.getYear());
            foundDictionary.setDescription(dictionary.getDescription());
            atomicReference.set(Optional.of(
                    dictionaryRepository.save(foundDictionary)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (dictionaryRepository.existsById(id)) {
            dictionaryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
