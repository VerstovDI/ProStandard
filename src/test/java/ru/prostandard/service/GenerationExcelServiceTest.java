package ru.prostandard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.prostandard.repository.CompetenceModelRepository;

@SpringBootTest
class GenerationExcelServiceTest {
    @Autowired
    GenerationExcelService generationExcelService;
    @Autowired
    CompetenceModelRepository competenceModelRepository;

    @Test
    @Transactional
    void generate() {
        generationExcelService.generate(competenceModelRepository.getOne(1));
    }
}