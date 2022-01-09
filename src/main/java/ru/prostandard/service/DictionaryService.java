package ru.prostandard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.SearchDTO;
import ru.prostandard.repository.EducationLevelRepository;
import ru.prostandard.repository.MajorRepository;
import ru.prostandard.repository.SpecializationRepository;

/**
* Сервис ведения справочной информации программы.
 * Включает в себя справочники по уровню образования, направлению подготовки (специальности),
 * программе подготовки (специализации)
* */
@Service
@RequiredArgsConstructor
public class DictionaryService {

    private final EducationLevelRepository educationLevelRepository;
    private final MajorRepository majorRepository;
    private final SpecializationRepository specializationRepository;

    /*public SearchDTO createRequest(SearchDTO searchDTO) {
        // TODO: где-то тут работает интеллектуальный сервис
        // SearchFilter searchFilter = ...; -> составляем из dictionaryDataDTO и состояния базы
        // intellectualService.selectStandards(SearchFilter searchFilter); -> "умная выборка стандартов"

        return new Object;
    }*/
}
