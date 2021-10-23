package ru.prostandard.service.intellisearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostandard.model.dto.StandardDTO;
import ru.prostandard.model.search.SearchFilter;
import ru.prostandard.repository.standard.StandardRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Интеллектуальный сервис формирования выборки профстандартов.
 * Данный сервис является реализацией системы поддержки принятия решений (DSS) пользователя
 * по выборке необходимых профессиональных стандартов.
 */
@Service
public class IntelliSelectionService {

    /**
     * Список, хранящий последнюю проведённую выборку профессиональных стандартов
     */
    private List<StandardDTO> profstandards;

    @Autowired
    private StandardRepository standardRepository;

    public List<StandardDTO> getProfstandards(StandardDTO standardDTO) {

        List<SearchFilter> searchFilters = new ArrayList<>();

        searchFilters.add(standardDTO);

        profstandards = standardRepository.getStandards(searchFilters);

        return profstandards;
    }

    /* public List<StandardDTO> getProfstandardsWithPostProcessing(...) {
        List<StandardDTO> profstandards = this.getProfstandards(...);
        ..some custom post-processing logic...
        return
        }
     */


}
