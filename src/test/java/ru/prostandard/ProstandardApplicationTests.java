package ru.prostandard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.prostandard.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProstandardApplicationTests {

    @Autowired
    StandardRepository standardRepository;
    @Autowired
    GeneralizedWorkFunctionRepository gwfRepository;
    @Autowired
    PossibleJobTitleRepository possibleJobTitleRepository;
    @Autowired
    ParticularWorkFunctionRepository particularWorkFunctionRepository;
    @Autowired
    LaborActionRepository laborActionRepository;
    @Autowired
    RequiredSkillRepository requiredSkillRepository;
    @Autowired
    NecessaryKnowledgeRepository necessaryKnowledgeRepository;

    @Test
    void findStandardsByEducationalLevelAndByKeyWordsTest() {
        System.out.println(standardRepository.findStandardsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "Архитектор | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList()));
    }

    @Test
    void findGeneralizedWorkFunctionsByEducationalLevelAndByKeyWordsTest() {
        System.out.println(gwfRepository.findGeneralizedWorkFunctionsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "База & данных | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList()));
    }

    @Test
    void findPossibleJobTitleByEducationalLevelAndByKeyWordsTest() {
        System.out.println(possibleJobTitleRepository.findPossibleJobTitlesByEducationalLevelAndByKeyWords
                ("магистратура ", "База & данных ")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList()));
    }


    @Test
    void findParticularWorkFunctionsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = particularWorkFunctionRepository.findParticularWorkFunctionsByEducationalLevelAndByKeyWords
                ("магистратура ", "База & данных ")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        System.out.println(ids);
        //  assertEquals(ids.size(), 10);
    }

    @Test
    void findLaborActionsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = laborActionRepository.findLaborActionsByEducationalLevelAndByKeyWords
                ("специалитет", "аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        //assertTrue(ids.containsAll(Arrays.asList(35132L, 104455L, 118672L)));
        System.out.println(ids);
        //  assertEquals(ids.size(), 53);
    }

    @Test
    void findRequiredSkillsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = requiredSkillRepository.findRequiredSkillsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "База & данных | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        //assertTrue(ids.containsAll(Arrays.asList(1762L, 1883L, 6963L)));
        System.out.println(ids);
    }

    @Test
    void findNecessaryKnowledgesByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = necessaryKnowledgeRepository.findNecessaryKnowledgesByEducationalLevelAndByKeyWords
                ("специалитет", "База & данных")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        //assertTrue(ids.containsAll(Arrays.asList(2327L, 22471L, 30445L)));
        System.out.println(ids);
    }

}
