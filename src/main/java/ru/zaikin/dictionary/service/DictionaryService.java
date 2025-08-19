package ru.zaikin.dictionary.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DictionaryService {
    private final Map<String, String> dictionary = new HashMap<>();

    public DictionaryService() {
        dictionary.put("привет", "hello");
        dictionary.put("мир", "world");
        dictionary.put("кот", "cat");
        dictionary.put("собака", "dog");
    }

    public String translate(String word) {
        return dictionary.getOrDefault(word, "isnt placed");
    }
}
