package ru.prostandard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ParsingServiceTest {
@Autowired
ParsingService parsingService;
    @Test
    void parse() {
        String dirToSave = Paths.get("resources").toAbsolutePath().toString();
        for (int i = 1; i <= 11; i++) { //
            String numberToParse = "01" + String.format("%03d", i);
            parsingService.parse(numberToParse, dirToSave);
        }
    }
}