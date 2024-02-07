package com.dictionary.back.service.impl;

import com.dictionary.back.entity.word.Category;
import com.dictionary.back.repository.CategoryRepository;
import com.dictionary.back.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Optional<Category> updateById(UUID id, Category category) {

        AtomicReference<Optional<Category>> atomicReference = new AtomicReference<>();

        categoryRepository.findById(id).ifPresentOrElse(foundCategory -> {
            foundCategory.setName(category.getName());
            atomicReference.set(Optional.of(
                    categoryRepository.save(foundCategory)));
        }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean deleteById(UUID id) {

        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
