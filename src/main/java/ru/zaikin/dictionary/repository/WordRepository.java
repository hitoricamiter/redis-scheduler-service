package ru.zaikin.dictionary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zaikin.dictionary.entity.Word;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long> {
    Optional<Word> findByRussian(String word);
}
