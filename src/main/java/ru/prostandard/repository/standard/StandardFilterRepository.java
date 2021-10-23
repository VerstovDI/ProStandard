package ru.prostandard.repository.standard;

import ru.prostandard.model.dto.StandardDTO;
import ru.prostandard.model.search.SearchFilter;

import java.util.Collection;
import java.util.List;

/**
 * Интерфейс работы со стандартами в соответствие с заданными фильтрами.
 * Служит дополнительным звеном при получении стандартов из репозитория для
 * гибкости внедрения возможных изменений в логике формирования итогового результата
 */
public interface StandardFilterRepository {

    /**
     * Метод получения стандартов, содержащий максимум возможной информации для их поиска
     * @param searchFilters Поисковые фильтры, используемые для нахождения стандарта
     * @return Список найденных профессиональных стандартов
     */
    List<StandardDTO> getStandards(List<SearchFilter> searchFilters);

}
