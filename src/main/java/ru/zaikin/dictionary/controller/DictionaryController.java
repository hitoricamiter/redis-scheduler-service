package ru.zaikin.dictionary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zaikin.dictionary.service.DictionaryService;

@RestController
public class DictionaryController {


    private final DictionaryService dictionaryService;

    public DictionaryController(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/translate")
    public String translate(@RequestParam String word) {
        return dictionaryService.translate(word);
    }
}
