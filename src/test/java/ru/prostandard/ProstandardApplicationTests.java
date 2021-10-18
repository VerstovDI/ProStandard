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
        assertTrue(standardRepository.findStandardsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "Архитектор | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList())
                .containsAll(
                        Arrays.asList(1212L, 1250L, 1342L, 1344L, 1382L)));
    }

    @Test
    void findGeneralizedWorkFunctionsByEducationalLevelAndByKeyWordsTest() {
        assertTrue(gwfRepository.findGeneralizedWorkFunctionsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "База & данных | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList())
                .containsAll(
                        Arrays.asList(4511L)));
    }

    @Test
    void findPossibleJobTitleByEducationalLevelAndByKeyWordsTest() {
        assertTrue(possibleJobTitleRepository.findPossibleJobTitlesByEducationalLevelAndByKeyWords
                ("магистратура ", "База & данных ")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList())
                .containsAll(
                        Arrays.asList(11027L, 11426L, 11437L, 11443L)));
    }


    @Test
    void findParticularWorkFunctionsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = particularWorkFunctionRepository.findParticularWorkFunctionsByEducationalLevelAndByKeyWords
                ("магистратура ", "База & данных ")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        assertTrue(ids.containsAll(Arrays.asList(12558L, 13178L, 15625L)));
        //  assertEquals(ids.size(), 10);
    }

    @Test
    void findLaborActionsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = laborActionRepository.findLaborActionsByEducationalLevelAndByKeyWords
                ("специалитет", "аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        assertTrue(ids.containsAll(Arrays.asList(35132L, 104455L, 118672L)));
        //  assertEquals(ids.size(), 53);
    }

    @Test
    void findRequiredSkillsByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = requiredSkillRepository.findRequiredSkillsByEducationalLevelAndByKeyWords
                ("магистратура | специалитет", "База & данных | аналитик")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        assertTrue(ids.containsAll(Arrays.asList(1762L, 1883L, 6963L)));
    }

    @Test
    void findNecessaryKnowledgesByEducationalLevelAndByKeyWordsTest() {
        List<Long> ids = necessaryKnowledgeRepository.findNecessaryKnowledgesByEducationalLevelAndByKeyWords
                ("специалитет", "База & данных")
                .stream().flatMapToLong(LongStream::of).boxed().collect(Collectors.toList());
        assertTrue(ids.containsAll(Arrays.asList(2327L, 22471L, 30445L)));
    }

}
