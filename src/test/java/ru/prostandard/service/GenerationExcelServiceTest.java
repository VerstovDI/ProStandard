package ru.prostandard.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import ru.prostandard.repository.CompetenceModelRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

@SpringBootTest
class GenerationExcelServiceTest {
    @Autowired
    GenerationExcelService generationExcelService;
    @Autowired
    CompetenceModelRepository competenceModelRepository;

    @Test
    @Transactional
    void generate()  {
        File file = new File("src/main/resources");
        String dirToSave = file.getPath() + File.separator + "excels";

        generationExcelService.generate(competenceModelRepository.getOne(1),dirToSave);
    }
}