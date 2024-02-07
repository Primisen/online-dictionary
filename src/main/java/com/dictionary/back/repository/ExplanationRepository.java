package com.dictionary.back.repository;

import com.dictionary.back.entity.word.Explanation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExplanationRepository extends JpaRepository<Explanation, UUID> {
}
