package ru.zaikin.dictionary.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.zaikin.dictionary.entity.Word;
import ru.zaikin.dictionary.repository.WordRepository;
import org.springframework.cache.annotation.Cacheable;

@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final WordRepository wordRepository;

    @PersistenceContext
    EntityManager em;

    @Cacheable(value = "words", key = "#russian")
    @Transactional
    public String translate(String russian) {

        em.createNativeQuery("INSERT INTO word_requests (russian) VALUES (?)")
                .setParameter(1, russian)
                .executeUpdate();


        return wordRepository.findByRussian(russian)
                .map(Word::getEnglish)
                .orElse("not found");
    }
}
