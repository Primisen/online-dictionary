package com.dictionary.back.repository;

import com.dictionary.back.entity.online_dictionary.BelRuDictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BelRuDictionaryRepository extends JpaRepository<BelRuDictionary, UUID> {
}
