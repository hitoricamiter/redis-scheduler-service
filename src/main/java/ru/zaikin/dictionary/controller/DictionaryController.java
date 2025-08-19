package ru.zaikin.Dictionary.Application.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zaikin.Dictionary.Application.service.DictionaryService;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService service;

    @GetMapping("/{russian}")
    public String translate(@PathVariable String russian) {
        return service.translate(russian);
    }
}
