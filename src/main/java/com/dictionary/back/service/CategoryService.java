package com.dictionary.back.service;

import com.dictionary.back.entity.word.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    Category create(Category newCategory);

    List<Category> findAll();

    Optional<Category> findById(UUID id);

    Optional<Category> updateById(UUID id, Category category);

    Boolean deleteById(UUID id);
}
